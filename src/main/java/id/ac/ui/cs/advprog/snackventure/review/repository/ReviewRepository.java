package id.ac.ui.cs.advprog.snackventure.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.snackventure.review.model.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    List<Review> findAllByUserId(String userId);
    List<Review> findAllBySubscriptionBoxId(String subscriptionBoxId);
    @Query("SELECT r FROM Review r WHERE r.rating = :rating AND r.subscriptionBoxId = :subscriptionBoxId") //improve
    List<Review> findFilteredReviewByRating(@Param("rating") int rating, @Param("subscriptionBoxId") String subscriptionBoxId);   
}