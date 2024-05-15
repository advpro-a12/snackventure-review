package id.ac.ui.cs.advprog.snackventure.review.model;

import id.ac.ui.cs.advprog.snackventure.review.enums.ReviewStatus;
import id.ac.ui.cs.advprog.snackventure.review.status.ApprovedState;
import id.ac.ui.cs.advprog.snackventure.review.status.PendingState;
import id.ac.ui.cs.advprog.snackventure.review.status.RejectedState;
import id.ac.ui.cs.advprog.snackventure.review.status.ReviewState;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Setter
@Getter
@Entity
@Table(name = "review")
public class Review {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    // @GeneratedValue(strategy = GenerationType.AUTO)
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

    @JsonIgnore
    @Column(name="state", nullable = false)
    private String stateString;

    @Transient
    @JsonIgnore
    private ReviewState state;

    public Review(){
        this.stateString = ReviewStatus.PENDING.toString();
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
        this.stateString = ReviewStatus.PENDING.toString();
    }

    public void approve(){
        state.approve(this);
        this.stateString = "APPROVED";
    }

    @PostLoad
    public void postLoad() {
        switch (stateString) {
            case "APPROVED":
                this.state = new ApprovedState();
                break;
            case "REJECTED":
                this.state = new RejectedState();
                break;
            default:
                this.state = new PendingState();
                break;
        }
    }

    public void reject(){
        state.reject(this);
        this.stateString = "REJECTED";
    }
}
