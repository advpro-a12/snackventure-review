package id.ac.ui.cs.advprog.snackventure.review.service;

import id.ac.ui.cs.advprog.snackventure.review.model.Review;

import java.util.List;

public interface ReviewService {
    Review createReview(Review Review);
    List<Review> findAllReviews();
    List<Review> findAllByUserId(String userId);
    List<Review> findAllBySubscriptionBoxId(String subscriptionBoxId);
    Review findReviewById(String ReviewId);
    Review updateReview(Review Review);
    Review deleteReview(String ReviewId);
    List<Review> findFilteredReviewByRating(String subcriptionBoxId, int raitng);
} 
