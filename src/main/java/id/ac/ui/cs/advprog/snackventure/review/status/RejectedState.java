package id.ac.ui.cs.advprog.snackventure.review.status;

import id.ac.ui.cs.advprog.snackventure.review.model.Review;

public class RejectedState implements ReviewState {
    @Override
    public void approve(Review Review) {
        // Cannot approve a rejected Review
    }

    @Override
    public void reject(Review Review) {
        // Already rejected
    }

}
