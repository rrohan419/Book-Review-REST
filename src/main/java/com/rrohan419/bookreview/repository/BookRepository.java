package com.rrohan419.bookreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rrohan419.bookreview.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
