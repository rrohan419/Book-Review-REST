package com.rrohan419.bookreview.controller;

import com.rrohan419.bookreview.dto.ReviewRequest;
import com.rrohan419.bookreview.model.Review;
import com.rrohan419.bookreview.service.ReviewService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books/{bookId}/reviews")
public class ReviewController {
    private final ReviewService service;

    public ReviewController(ReviewService service) { this.service = service; }

    @PreAuthorize("hasRole('READER')")
    @PostMapping
    public ResponseEntity<?> add(@PathVariable Long bookId, @Valid @RequestBody ReviewRequest req, Authentication auth) {
        Long userId = deriveUserIdFromAuth(auth);
        Review r = new Review();
        r.setBookId(bookId);
        r.setUserId(userId);
        r.setRating(req.getRating());
        r.setComment(req.getComment());
        Review saved = service.add(r);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping
    public Page<Review> list(@PathVariable Long bookId, Pageable pageable) {
        return service.forBook(bookId, pageable);
    }

    private Long deriveUserIdFromAuth(Authentication auth) {
        String token = auth == null ? "" : auth.getName();
        if ("token-admin".equals(token)) return 1L;
        if ("token-reader".equals(token)) return 2L;
        return 999L;
    }
}
