package id.ac.ui.cs.advprog.snackventure.review.status;

import id.ac.ui.cs.advprog.snackventure.review.model.Review;

public interface ReviewState {
    void approve(Review review);
    void reject(Review review);
}
