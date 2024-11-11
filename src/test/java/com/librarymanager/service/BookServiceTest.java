package com.librarymanager.service;

import com.librarymanager.model.Book;
import com.librarymanager.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBook() {
        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setYear(2020);

        when(bookRepository.save(book)).thenReturn(book);
        Book savedBook = bookService.addBook(book);

        assertNotNull(savedBook);
        assertEquals("Title", savedBook.getTitle());
        assertEquals("Author", savedBook.getAuthor());
        assertEquals(2020, savedBook.getYear());
    }

    @Test
    public void testListBooks() {
        Book book1 = new Book();
        book1.setTitle("Title 1");
        book1.setAuthor("Author 1");
        book1.setYear(2020);

        Book book2 = new Book();
        book2.setTitle("Title 2");
        book2.setAuthor("Author 2");
        book2.setYear(2021);

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookService.listBooks();
        
        assertNotNull(books);
        assertEquals(2, books.size());
        assertEquals("Title 1", books.get(0).getTitle());
        assertEquals("Title 2", books.get(1).getTitle());
    }

    @Test
    public void testGetBookById() {
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setYear(2020);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        
        Optional<Book> foundBook = bookService.getBookById(bookId);
        
        assertTrue(foundBook.isPresent());
        assertEquals("Title", foundBook.get().getTitle());
    }

    @Test
    public void testUpdateBook() {
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Old Title");
        book.setAuthor("Old Author");
        book.setYear(2020);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        book.setTitle("New Title");
        book.setAuthor("New Author");

        when(bookRepository.save(book)).thenReturn(book);
        Book updatedBook = bookService.updateBook(bookId, book);

        assertNotNull(updatedBook);
        assertEquals("New Title", updatedBook.getTitle());
        assertEquals("New Author", updatedBook.getAuthor());
    }

    @Test
    public void testDeleteBook() {
        Long bookId = 1L;

        doNothing().when(bookRepository).deleteById(bookId);
        bookService.deleteBook(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }
}
