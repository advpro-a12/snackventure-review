package id.ac.ui.cs.advprog.snackventure.review.model;

import id.ac.ui.cs.advprog.snackventure.review.enums.ReviewStatus;
import id.ac.ui.cs.advprog.snackventure.review.status.PendingState;
import id.ac.ui.cs.advprog.snackventure.review.status.ReviewState;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "review")
public class Review {
    @Id
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String idReview;

    @Column(name="created_date", updatable = false, nullable = false)
    private Date createdDate;

    @Column(name="user_id",nullable = false)
    private String userId;

    @Column(nullable = false)
    private String review;

    @Column(nullable = false)
    private int rating;

    @Column(name="subscription_box_id",nullable = false)
    private String subscriptionBoxId;

    @Column(name="review_status", nullable = false)
    private ReviewStatus reviewStatus;

    @Transient
    private ReviewState state;

    public Review(){
        this.state = new PendingState();
    }

    public Review(String userId, String subscriptionBoxId, int rating,String review) {
        this.idReview = UUID.randomUUID().toString();
        this.createdDate= new Date();
        this.userId = userId;
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
