package id.ac.ui.cs.advprog.snackventure.review.service;

import id.ac.ui.cs.advprog.snackventure.review.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Review createReview(Review Review);
    List<Review> findAllReviews();
    List<Review> findAllByUserId(String userId);
    List<Review> findAllBySubscriptionBoxId(String subscriptionBoxId);
    Optional<Review> findReviewById(String ReviewId);
    Review updateReview(Review Review);
    void deleteReview(String ReviewId);
    List<Review> findFilteredReviewByRating(int rating, String subscriptionBoxId);
    Review updateReviewStatus(String idReview, String status);
} 
