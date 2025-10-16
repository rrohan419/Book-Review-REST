package com.rrohan419.bookreview.controller;

import com.rrohan419.bookreview.dto.ReviewRequest;
import com.rrohan419.bookreview.model.Review;
import com.rrohan419.bookreview.service.ReviewService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/books/{bookId}/reviews")
public class ReviewController {
    private final ReviewService service;

    public ReviewController(ReviewService service) { this.service = service; }

    @PreAuthorize("hasRole('READER')")
    @PostMapping("/{userId}/user")
    public ResponseEntity<Review> add(@PathVariable(name = "bookId") Long bookId, @Valid @RequestBody ReviewRequest req, @PathVariable(name = "userId") Long userId) {
        Review review = new Review();
        review.setBookId(bookId);
        review.setUserId(userId);
        review.setRating(req.getRating());
        review.setComment(req.getComment());
        Review savedReview = service.add(review);
        return ResponseEntity.status(201).body(savedReview);
    }

    @PreAuthorize("hasAnyRole('READER','ADMIN')")
    @GetMapping
    public Page<Review> list(@PathVariable(name ="bookId") Long bookId, Pageable pageable) {
        return service.forBook(bookId, pageable);
    }
}
