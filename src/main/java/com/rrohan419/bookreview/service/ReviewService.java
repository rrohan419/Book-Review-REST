package com.rrohan419.bookreview.service;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rrohan419.bookreview.model.Review;
import com.rrohan419.bookreview.repository.ReviewRepository;

@Service
@Transactional
public class ReviewService {
    private final ReviewRepository repo;

    public ReviewService(ReviewRepository repo) {
        this.repo = repo;
    }

    public Review add(Review r) {
        return repo.save(r);
    }

    public Page<Review> forBook(Long bookId, Pageable pageable) {
        return repo.findByBookId(bookId, pageable);
    }
}
