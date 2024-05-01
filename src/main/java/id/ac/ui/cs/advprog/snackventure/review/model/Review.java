package id.ac.ui.cs.advprog.snackventure.review.model;

import id.ac.ui.cs.advprog.snackventure.review.enums.ReviewStatus;
import id.ac.ui.cs.advprog.snackventure.review.status.PendingState;
import id.ac.ui.cs.advprog.snackventure.review.status.ReviewState;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Setter
@Getter

public class Review {
    private String idReview;
    private Date createdDate;
    private String customerId;
    private String review;
    private int rating;
    private String subscriptionBoxId;
    private ReviewStatus reviewStatus;
    private ReviewState state;


    public Review(String idReview, String customerId, String subscriptionBoxId, int rating,String review) {
        this.idReview = idReview;
        this.createdDate= new Date();
        this.customerId = customerId;
        this.subscriptionBoxId = subscriptionBoxId;
        this.reviewStatus = ReviewStatus.PENDING;
        this.rating = rating;
        this.review = review;
        this.state = new PendingState();
    }

    public void approve(){
        state.approve(this);
    }

    public void reject(){
        state.reject(this);
    }



}
