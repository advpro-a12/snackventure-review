package id.ac.ui.cs.advprog.snackventure.review.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.snackventure.review.enums.ReviewStatus;
import id.ac.ui.cs.advprog.snackventure.review.model.Review;
import id.ac.ui.cs.advprog.snackventure.review.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired 
    private ReviewRepository reviewRepository;

    @Override
    public Review createReview(Review review){
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> findAllReviews(){
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> findAllByUserId(String userId){
        return reviewRepository.findAllByUserId(userId);
    }

    @Override
    public List<Review> findAllBySubscriptionBoxId(String subscriptionBoxId){
        return reviewRepository.findAllBySubscriptionBoxId(subscriptionBoxId);
    }

    @Override
    public Optional<Review> findReviewById(String reviewId){
        return reviewRepository.findById(reviewId);
    }

    @Override
    public Review updateReview(Review Review){
        return reviewRepository.save(Review);
    }

    @Override
    public void deleteReview(String ReviewId){
        reviewRepository.deleteById(ReviewId);
    }

    @Override
    public List<Review> findAllByRatingAndSubscriptionBoxId(int rating, String subscriptionBoxId){
        return reviewRepository.findAllByRatingAndSubscriptionBoxId(rating, subscriptionBoxId);
    }

    @Override
    public Review updateReviewStatus(String idReview, String status) {
        Review review = reviewRepository.findById(idReview)
            .orElseThrow(() -> new IllegalArgumentException("Review not found with ID: " + idReview));
        if (status.equalsIgnoreCase(ReviewStatus.APPROVED.toString())) {
            review.approve();
        } else if (status.equalsIgnoreCase(ReviewStatus.REJECTED.toString())) {
            review.reject();
        } else {
            throw new IllegalArgumentException("Invalid status");
        }

        reviewRepository.save(review);
        return review;
    }

}
