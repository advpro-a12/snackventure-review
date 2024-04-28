package id.ac.ui.cs.advprog.snackventure.review.status;

import id.ac.ui.cs.advprog.snackventure.review.model.Review;

public class ApprovedState implements ReviewState {
    @Override
    public void approve(Review Review) {
        // already approved
    }

    @Override
    public void reject(Review Review) {
        // cannot reject approved review
    }
}
