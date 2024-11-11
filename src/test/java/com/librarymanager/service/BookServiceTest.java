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
import static org.mockito.ArgumentMatchers.any;
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
        // Configuração inicial de um livro existente
        Book existingBook = new Book();
        existingBook.setId(1L);
        existingBook.setTitle("Old Title");
        existingBook.setAuthor("Old Author");
        existingBook.setYear(2019);
    
        // Configuração do livro atualizado
        Book updatedBook = new Book();
        updatedBook.setTitle("New Title");
        updatedBook.setAuthor("New Author");
        updatedBook.setYear(2020);
    
        // Configuração dos mocks
        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);
    
        // Chamada ao método de atualização no serviço
        Book result = bookService.updateBook(1L, updatedBook);
    
        // Verificações
        assertNotNull(result, "O livro atualizado não deve ser nulo");
        assertEquals("New Title", result.getTitle(), "O título do livro não foi atualizado corretamente");
        assertEquals("New Author", result.getAuthor(), "O autor do livro não foi atualizado corretamente");
    
        // Verifica se o método save foi chamado uma vez
        verify(bookRepository, times(1)).save(any(Book.class));
    }
    

    @Test
    public void testDeleteBook() {
        Long bookId = 1L;

        doNothing().when(bookRepository).deleteById(bookId);
        bookService.deleteBook(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }
}
