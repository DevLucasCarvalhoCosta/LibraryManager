package com.librarymanager.service;

import com.librarymanager.model.Book;
import com.librarymanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void updateAvailableCopies(Long bookId, int adjustment) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        book.setAvailableCopies(book.getAvailableCopies() + adjustment);
        bookRepository.save(book);
    }
}
