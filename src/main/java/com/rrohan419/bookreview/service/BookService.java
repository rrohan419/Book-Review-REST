package com.rrohan419.bookreview.service;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rrohan419.bookreview.model.Book;
import com.rrohan419.bookreview.repository.BookRepository;

import java.util.Optional;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final ExternalBookLookupService lookupService;

    public BookService(BookRepository bookRepository, ExternalBookLookupService lookupService) {
        this.bookRepository = bookRepository;
        this.lookupService = lookupService;
    }

    public Page<Book> list(Pageable p) {
        return bookRepository.findAll(p);
    }

    public Optional<Book> get(Long id) {
        return bookRepository.findById(id);
    }

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public java.util.Map<String, String> fetchInfoFromIsbn(String isbn) {
        return lookupService.lookupByIsbn(isbn);
    }
}
