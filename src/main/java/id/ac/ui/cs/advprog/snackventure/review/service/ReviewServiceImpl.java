package id.ac.ui.cs.advprog.snackventure.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.snackventure.review.model.Review;
import id.ac.ui.cs.advprog.snackventure.review.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired 
    private ReviewRepository reviewRepository;

    @Override
    public Review createReview(Review Review){
        return reviewRepository.create(Review);
    }

    @Override
    public List<Review> findAllReviews(){
        return (List<Review>) reviewRepository.findAll();
    }

    @Override
    public List<Review> findAllByUserId(String userId){
        return (List<Review>) reviewRepository.findAllByUserId(userId);
    }

    @Override
    public List<Review> findAllBySubscriptionBoxId(String subscriptionBoxId){
        return (List<Review>) reviewRepository.findAllBySubscriptionId(subscriptionBoxId);
    }

    @Override
    public Review findReviewById(String ReviewId){
        return reviewRepository.findById(ReviewId);
    }

    @Override
    public Review updateReview(Review Review){
        return reviewRepository.edit(Review);
    }

    @Override
    public Review deleteReview(String ReviewId){
        return reviewRepository.delete(ReviewId);
    }

    @Override
    public List<Review> findFilteredReviewByRating(String subcriptionBoxId, int raitng){
        return reviewRepository.findAllFilteredReviewByRating(subcriptionBoxId, raitng);
    }
}
