package id.ac.ui.cs.advprog.snackventure.review.model;

import id.ac.ui.cs.advprog.snackventure.review.enums.ReviewStatus;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.UUID;


@Setter
@Getter

public class Review {
    private UUID idReview;
    private Date createdDate;
    private String customerId;
    private String review;
    private int rating;
    private String subscriptionBoxId;
    private ReviewStatus reviewStatus;


    public Review(String customerId, String subscriptionBoxId, int rating,String review) {
        this.idReview = UUID.randomUUID();
        this.createdDate= new Date();
        this.customerId = customerId;
        this.subscriptionBoxId = subscriptionBoxId;
        this.reviewStatus = ReviewStatus.PENDING;
        this.rating = rating;
        this.review = review;
    }
}
