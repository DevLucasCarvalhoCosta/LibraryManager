package com.librarymanager.service;

import com.librarymanager.model.Book;
import com.librarymanager.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Test
    public void testAddBook() {
        Book book = new Book();
        book.setTitle("Title");
        book.setAuthor("Author");
        book.setYear(2020);

        Book savedBook = bookService.addBook(book);

        assertNotNull(savedBook);
        assertEquals("Title", savedBook.getTitle());
    }

    @Test
    public void testListBooks() {
        List<Book> books = bookService.listBooks();
        assertNotNull(books);
    }

    @Test
    public void testGetBookById() {
        Book book = new Book();
        book.setTitle("Test Title");
        bookRepository.save(book);

        Optional<Book> foundBook = bookService.getBookById(book.getId());
        assertTrue(foundBook.isPresent());
    }

    @Test
    public void testUpdateBook() {
        Book existingBook = new Book();
        existingBook.setTitle("Old Title");
        bookRepository.save(existingBook);

        Book updatedBook = new Book();
        updatedBook.setTitle("New Title");
        Book result = bookService.updateBook(existingBook.getId(), updatedBook);

        assertEquals("New Title", result.getTitle());
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book();
        bookRepository.save(book);
        bookService.deleteBook(book.getId());

        Optional<Book> deletedBook = bookRepository.findById(book.getId());
        assertFalse(deletedBook.isPresent());
    }
}
