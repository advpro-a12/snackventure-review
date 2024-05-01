package id.ac.ui.cs.advprog.snackventure.review.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.snackventure.review.enums.ReviewStatus;
import id.ac.ui.cs.advprog.snackventure.review.status.ApprovedState;
import id.ac.ui.cs.advprog.snackventure.review.status.RejectedState;



public class ReviewTest {
    private Review review;

    @BeforeEach
    public void setUp() {
        String idReview = "ulasan1";
        int rating = 4;
        String reviewText = "Great snacks!";
        String customerId = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
        String subscriptionBoxId = "99963276-4e60-4e9a-96ce-8d5a9957209d";

        review = new Review(idReview, customerId, subscriptionBoxId, rating, reviewText);
    }

    @Test
    public void testCreateReview() {
        assertNotNull(review);
        
        assertEquals("ulasan1",review.getIdReview());
        assertNotNull(review.getCreatedDate());

        assertEquals("07f9b8b0-7257-4434-a5b9-79c9703f0760", review.getCustomerId());
        assertEquals("99963276-4e60-4e9a-96ce-8d5a9957209d", review.getSubscriptionBoxId());
        assertEquals(ReviewStatus.PENDING, review.getReviewStatus());

        assertEquals(4, review.getRating());
        assertEquals("Great snacks!", review.getReview());
    }
    
    @Test
    public void testReviewStatusApprove() {
        review.approve();
        assertEquals(ReviewStatus.APPROVED, review.getReviewStatus());
        assertInstanceOf(ApprovedState.class, review.getState());
    }

    @Test
    public void testReviewStatusReject() {
        review.reject();
        assertEquals(ReviewStatus.REJECTED, review.getReviewStatus());
        assertInstanceOf(RejectedState.class, review.getState());
    }

    @Test
    public void testReviewStatusApproveThenReject(){
        review.approve();
        review.reject();
        assertEquals(ReviewStatus.APPROVED, review.getReviewStatus());
        assertInstanceOf(ApprovedState.class, review.getState());
    }

    @Test
    public void testReivewStatusRejectThenApprove(){
        review.reject();
        review.approve();
        assertEquals(ReviewStatus.REJECTED, review.getReviewStatus());
        assertInstanceOf(RejectedState.class, review.getState());
    }

}   
