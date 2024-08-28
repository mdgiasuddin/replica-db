package org.example.replicadb.service;

import lombok.RequiredArgsConstructor;
import org.example.replicadb.BookRepository;
import org.example.replicadb.dto.request.BookRequest;
import org.example.replicadb.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public void saveBook(BookRequest request) {
        Book book = new Book();
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setPrice(request.price());

        bookRepository.save(book);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
