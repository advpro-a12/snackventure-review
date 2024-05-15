package id.ac.ui.cs.advprog.snackventure.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.snackventure.review.model.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    List<Review> findAllByUserId(String userId);
    List<Review> findAllBySubscriptionBoxId(String subscriptionBoxId);
    List<Review> findAllByRatingAndSubscriptionBoxId(int rating, String subscriptionBoxId);
}