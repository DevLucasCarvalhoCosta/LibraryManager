package com.librarymanager.controller;

import com.librarymanager.model.Book;
import com.librarymanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PatchMapping("/{bookId}/updateCopies")
    public void updateAvailableCopies(@PathVariable Long bookId, @RequestParam int adjustment) {
        bookService.updateAvailableCopies(bookId, adjustment);
    }
}
