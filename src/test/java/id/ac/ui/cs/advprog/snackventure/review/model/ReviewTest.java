package id.ac.ui.cs.advprog.snackventure.review.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.snackventure.review.enums.ApprovalStatus;


public class ReviewTest {
    private Review review;
    private final String customerId = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
    private final String subscriptionBoxId = "99963276-4e60-4e9a-96ce-8d5a9957209d";

    @BeforeEach
    public void setUp() {
        int rating = 4;
        String reviewText = "Great snacks!";

        review = new Review(customerId, subscriptionBoxId, rating, reviewText);
    }

    @Test
    public void testCreateReview() {
        assertNotNull(review);

        assertNotNull(review.getIdReview());
        assertNotNull(review.getCreatedDate());

        assertEquals("07f9b8b0-7257-4434-a5b9-79c9703f0760", review.getCustomerId());
        assertEquals("99963276-4e60-4e9a-96ce-8d5a9957209d", review.getSubscriptionBoxId());
        assertEquals(ApprovalStatus.PENDING.getValue(), review.getApprovalStatus());

        assertEquals(4, review.getRating());
        assertEquals("Great snacks!", review.getReview());
    }
    
    @Test
    public void testSetApprovalStatusToApproved() {
        review.setApprovalStatus(ApprovalStatus.APPROVED.getValue());
        assertEquals(ApprovalStatus.APPROVED.getValue(), review.getApprovalStatus());
    }

    @Test
    public void testSetApprovalStatusToRejected() {
        review.setApprovalStatus(ApprovalStatus.REJECTED.getValue());
        assertEquals(ApprovalStatus.REJECTED.getValue(), review.getApprovalStatus());
    }
    
}   
