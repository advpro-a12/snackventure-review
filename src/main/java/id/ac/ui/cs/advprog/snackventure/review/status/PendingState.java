package id.ac.ui.cs.advprog.snackventure.review.status;

import id.ac.ui.cs.advprog.snackventure.review.enums.ReviewStatus;
import id.ac.ui.cs.advprog.snackventure.review.model.Review;

public class PendingState implements ReviewState {
    @Override
    public void approve(Review review){
        review.setReviewStatus(ReviewStatus.APPROVED);
        review.setState(new ApprovedState());
    }

    @Override
    public void reject(Review review){
        review.setReviewStatus(ReviewStatus.REJECTED);
        review.setState(new RejectedState());
    }
}
