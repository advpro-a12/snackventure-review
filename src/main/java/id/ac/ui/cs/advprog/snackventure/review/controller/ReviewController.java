package id.ac.ui.cs.advprog.snackventure.review.controller;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.ac.ui.cs.advprog.snackventure.review.model.Review;
import id.ac.ui.cs.advprog.snackventure.review.service.ReviewService;


@RestController
@EnableAsync
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Async
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @PostMapping("/{id}/create-review")
    public CompletableFuture<ResponseEntity<Review>> createReview(@PathVariable String id, @RequestBody Map<String, Object> requestBody){
        String idUser = requestBody.get("customerId").toString();
        String reviewText = requestBody.get("review").toString();
        int rating = Integer.parseInt(requestBody.get("rating").toString());
        Review review = new Review(idUser,id,rating,reviewText);
        Review createdReview = reviewService.createReview(review);
        return CompletableFuture.completedFuture(ResponseEntity.ok(createdReview));
    }

    @Async
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @GetMapping("/reviews")
    public CompletableFuture<ResponseEntity<List<Review>>> getAllReview() {
        List<Review> review = reviewService.findAllReviews();
        return CompletableFuture.completedFuture(ResponseEntity.ok(review));
    }

    @Async
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<Review>> getReviewById(@PathVariable String id){
        Optional<Review> optionalReview = reviewService.findReviewById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            return CompletableFuture.completedFuture(ResponseEntity.ok(review));
        } else {
            return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
        }
    }

    @Async
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @GetMapping("user/{id}")
    public CompletableFuture<ResponseEntity<List<Review>>> getReviewByUserId(@PathVariable String id){
        List<Review> reviews = reviewService.findAllByUserId(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok(reviews));
    }

    @Async
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @GetMapping("subscription-box/{id}")
    public CompletableFuture<ResponseEntity<List<Review>>> getReviewBySubscriptionBoxId(@PathVariable String id){
        List<Review> reviews = reviewService.findAllBySubscriptionBoxId(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok(reviews));
    }

    @Async
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{id}/change-status")
    public CompletableFuture<ResponseEntity<Review>> updateApprovalStatus(@PathVariable String id, @RequestBody Map<String, String> requestBody) {
        String status = requestBody.get("status");
        Review updatedReviewStatus = reviewService.updateReviewStatus(id, status);
        return CompletableFuture.completedFuture(ResponseEntity.ok(updatedReviewStatus));
    }

    
    @Async
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @DeleteMapping("/{id}/delete-review")
    public CompletableFuture<ResponseEntity<Review>> deleteReview(@PathVariable String id){
        reviewService.deleteReview(id);
        return CompletableFuture.completedFuture(ResponseEntity.ok().build());
    }

    @Async
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @PatchMapping("/{id}/update-review")
    public CompletableFuture<ResponseEntity<Review>> updateReview(@PathVariable String id, @RequestBody Map<String, String> requestBody) {
        Optional<Review> optionalReview = reviewService.findReviewById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setReview(requestBody.get("review"));
            review.setRating(Integer.parseInt(requestBody.get("rating"))); 
            review.setCreatedDate(new Date());
            Review updateReview = reviewService.updateReview(review);
            return CompletableFuture.completedFuture(ResponseEntity.ok(updateReview));
        } else {
            return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
        }
    }
    
    @Async
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CUSTOMER')")
    @GetMapping("/subscription-box/{id}/filter")
    public CompletableFuture<ResponseEntity<List<Review>>> filterReviewsBySubscriptionBoxIdAndRating(@PathVariable("id") String id, @RequestBody Map<String,String> requestBody) {
        List<Review> reviews = reviewService.findAllByRatingAndSubscriptionBoxId(Integer.parseInt(requestBody.get("rating")), id);
        return CompletableFuture.completedFuture(ResponseEntity.ok(reviews));
    }
}
