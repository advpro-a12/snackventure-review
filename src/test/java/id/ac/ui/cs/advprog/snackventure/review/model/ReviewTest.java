package id.ac.ui.cs.advprog.snackventure.review.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.snackventure.review.enums.ReviewStatus;



public class ReviewTest {
    private Review review;

    @BeforeEach
    public void setUp() {
        int rating = 4;
        String reviewText = "Great snacks!";
        String customerId = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
        String subscriptionBoxId = "99963276-4e60-4e9a-96ce-8d5a9957209d";

        review = new Review(customerId, subscriptionBoxId, rating, reviewText);
    }

    @Test
    public void testCreateReview() {
        assertNotNull(review);

        assertNotNull(review.getIdReview());
        assertNotNull(review.getCreatedDate());

        assertEquals("07f9b8b0-7257-4434-a5b9-79c9703f0760", review.getCustomerId());
        assertEquals("99963276-4e60-4e9a-96ce-8d5a9957209d", review.getSubscriptionBoxId());
        assertEquals(ReviewStatus.PENDING, review.getReviewStatus());

        assertEquals(4, review.getRating());
        assertEquals("Great snacks!", review.getReview());
    }
    
    @Test
    public void testSetReviewStatusToApproved() {
        review.setReviewStatus(ReviewStatus.APPROVED);
        assertEquals(ReviewStatus.APPROVED, review.getReviewStatus());
    }

    @Test
    public void testSetReviewStatusToRejected() {
        review.setReviewStatus(ReviewStatus.REJECTED);
        assertEquals(ReviewStatus.REJECTED, review.getReviewStatus());
    }

}   
