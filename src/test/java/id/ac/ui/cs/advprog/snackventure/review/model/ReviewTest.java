package id.ac.ui.cs.advprog.snackventure.review.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.snackventure.review.enums.ReviewStatus;
import id.ac.ui.cs.advprog.snackventure.review.status.ApprovedState;
import id.ac.ui.cs.advprog.snackventure.review.status.PendingState;
import id.ac.ui.cs.advprog.snackventure.review.status.RejectedState;



public class ReviewTest {
    private Review review;

    @BeforeEach
    public void setUp() {
        int rating = 4;
        String reviewText = "Great snacks!";
        String userId = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
        String subscriptionBoxId = "99963276-4e60-4e9a-96ce-8d5a9957209d";
        review = new Review(userId, subscriptionBoxId, rating, reviewText);
    }

    @Test
    public void testCreateReview() {
        assertNotNull(review);
        assertNotNull(review.getIdReview());
        assertNotNull(review.getCreatedDate());

        assertEquals("07f9b8b0-7257-4434-a5b9-79c9703f0760", review.getUserId());
        assertEquals("99963276-4e60-4e9a-96ce-8d5a9957209d", review.getSubscriptionBoxId());
        assertEquals(ReviewStatus.PENDING, review.getReviewStatus());
        assertEquals(4, review.getRating());
        assertEquals("Great snacks!", review.getReview());
    }

    @Test
    public void testSetUserId() {
        String newUserId = "123e4567-e89b-12d3-a456-426614174000";
        review.setUserId(newUserId);
        assertEquals(newUserId, review.getUserId());
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

    @Test
    public void testReviewStatusApproveThenApprove(){
        review.approve();
        review.approve();
        assertEquals(ReviewStatus.APPROVED, review.getReviewStatus());
        assertInstanceOf(ApprovedState.class, review.getState());
    }

    @Test
    public void testReivewStatusRejectThenReject(){
        review.reject();
        review.reject();
        assertEquals(ReviewStatus.REJECTED, review.getReviewStatus());
        assertInstanceOf(RejectedState.class, review.getState());
    }


    @Test
    public void testPostLoadWithApprovedState() {
        review.setStateString("APPROVED");
        review.postLoad();
        assertEquals("APPROVED", review.getStateString());
        assertInstanceOf(ApprovedState.class, review.getState());
    }

    @Test
    public void testPostLoadWithRejectedState() {
        review.setStateString("REJECTED");
        review.postLoad();
        assertEquals("REJECTED", review.getStateString());
        assertInstanceOf(RejectedState.class, review.getState());
    }

    @Test
    public void testPostLoadWithPendingState() {
        review.setStateString("PENDING");
        review.postLoad();
        assertEquals("PENDING", review.getStateString());
        assertInstanceOf(PendingState.class, review.getState());
    }

    @Test
    public void testPostLoadWithUnknownState() {
        review.setStateString("UNKNOWN");
        review.postLoad();
        assertEquals("UNKNOWN", review.getStateString());
        assertInstanceOf(PendingState.class, review.getState());
    }

    @Test
    public void testPendingValue() {
        assertEquals("PENDING", ReviewStatus.PENDING.getValue());
    }

    @Test
    public void testApprovedValue() {
        assertEquals("APPROVED", ReviewStatus.APPROVED.getValue());
    }

    @Test
    public void testRejectedValue() {
        assertEquals("REJECTED", ReviewStatus.REJECTED.getValue());
    }
}   
