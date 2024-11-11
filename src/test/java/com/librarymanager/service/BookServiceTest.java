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
        book.setCopiesAvailable(5); // Novo campo para definir cópias disponíveis

        Book savedBook = bookService.saveBook(book);

        assertNotNull(savedBook);
        assertEquals("Title", savedBook.getTitle());
        assertEquals(5, savedBook.getCopiesAvailable());
    }

    @Test
    public void testListBooks() {
        List<Book> books = bookService.getAllBooks();
        assertNotNull(books);
    }

    @Test
    public void testGetBookById() {
        Book book = new Book();
        book.setTitle("Test Title");
        book.setCopiesAvailable(3); // Definindo número de cópias
        bookRepository.save(book);

        // Buscando livro por ID
        Optional<Book> foundBook = bookRepository.findById(book.getId());
        assertTrue(foundBook.isPresent());
        assertEquals(3, foundBook.get().getCopiesAvailable());
    }

    @Test
    public void testUpdateAvailableCopies() {
        // Teste para atualizar o número de cópias disponíveis
        Book book = new Book();
        book.setTitle("Book Title");
        book.setCopiesAvailable(5);
        bookRepository.save(book);

        // Atualizando o número de cópias disponíveis
        bookService.updateAvailableCopies(book.getId(), 3);
        
        Optional<Book> updatedBook = bookRepository.findById(book.getId());
        assertTrue(updatedBook.isPresent());
        assertEquals(8, updatedBook.get().getCopiesAvailable()); // 5 + 3
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book();
        book.setCopiesAvailable(1); // Definindo cópias
        bookRepository.save(book);
        bookService.deleteBook(book.getId());

        Optional<Book> deletedBook = bookRepository.findById(book.getId());
        assertFalse(deletedBook.isPresent());
    }
}
