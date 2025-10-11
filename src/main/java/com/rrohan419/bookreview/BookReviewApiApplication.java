package com.rrohan419.bookreview;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rrohan419.bookreview.model.Book;
import com.rrohan419.bookreview.repository.BookRepository;

@SpringBootApplication
public class BookReviewApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookReviewApiApplication.class, args);
    }

    @Bean
    CommandLineRunner seed(BookRepository bookRepository) {
        return args -> {
            if (bookRepository.count() == 0) {
                bookRepository.save(new Book(null, "The Hobbit", "J.R.R. Tolkien", java.time.LocalDate.parse("1937-09-21")));
                bookRepository.save(new Book(null, "1984", "George Orwell", java.time.LocalDate.parse("1949-06-08")));
                bookRepository.save(new Book(null, "To Kill a Mockingbird", "Harper Lee", java.time.LocalDate.parse("1960-07-11")));
            }
        };
    }
}
