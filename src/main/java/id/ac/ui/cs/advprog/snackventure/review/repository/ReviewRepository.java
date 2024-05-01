package id.ac.ui.cs.advprog.snackventure.review.repository;

import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.snackventure.review.model.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ReviewRepository {
    private List<Review> reviews = new ArrayList<>();

    public Review create(Review review){
        if (review.getIdReview() == null){
            review.setIdReview(UUID.randomUUID().toString());
        }
        reviews.add(review);
        return review;
    }
    
    public List<Review> findAll(){
        return new ArrayList<>(reviews);
    }

    public List<Review> findAllBySubscriptionId(String subscriptionboxId){
        List<Review> reviewBySubscription = new ArrayList<>();
        for (Review review:reviews){
            if (review.getSubscriptionBoxId().equals(subscriptionboxId));
                reviewBySubscription.add(review);
        }
        return reviewBySubscription;
    }

    public List<Review> findAllByUserId(String userId){
        List<Review> reviewByUserId = new ArrayList<>();
        for (Review review:reviews){
            if (review.getSubscriptionBoxId().equals(userId));
                reviewByUserId.add(review);
        }
        return reviewByUserId;
    }

    public Review findById(String reviewId){
        return reviews.stream()
                .filter(review -> review.getIdReview().equals(reviewId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid Review Id:" + reviewId)
                );
    }

    public Review edit(Review editedReview){
        String reviewId = editedReview.getIdReview();
        Review existingReview = findById(reviewId);
        int indexOfReview = reviews.indexOf(existingReview);
        reviews.set(indexOfReview,editedReview);
        return editedReview;
    }

    public Review delete(String reviewId){
        Review review = findById(reviewId);
        reviews.remove(review);
        return review;
    }

    public List<Review> findAllFilteredReviewByRating(String subscriptionBoxId, int rating) {
        List<Review> filteredReviewByRating = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getRating() == rating && review.getSubscriptionBoxId().equals(subscriptionBoxId)) {
                filteredReviewByRating.add(review);
            }
        }
        return filteredReviewByRating;
    }    
}
