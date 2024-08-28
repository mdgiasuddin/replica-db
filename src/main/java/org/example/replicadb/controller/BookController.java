package org.example.replicadb.controller;

import lombok.RequiredArgsConstructor;
import org.example.replicadb.dto.request.BookRequest;
import org.example.replicadb.entity.Book;
import org.example.replicadb.service.BookService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
@Validated
public class BookController {

    private final BookService bookService;

    @PostMapping
    public void saveBook(@RequestBody @Validated BookRequest request) {
        bookService.saveBook(request);
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }
}
