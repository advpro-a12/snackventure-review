package id.ac.ui.cs.advprog.snackventure.review.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.ac.ui.cs.advprog.snackventure.review.model.Review;
import id.ac.ui.cs.advprog.snackventure.review.service.ReviewService;

public class ReviewControllerTest {

    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private ReviewService reviewService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
    }

    @Test
    public void testCreateReview() throws Exception {
        String customerId1 = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
        String subscriptionBoxId1 = "99963276-4e60-4e9a-96ce-8d5a9957209d";
        int rating1 = 4;
        String reviewText1 = "Great snacks!";

        Review review = new Review(customerId1, subscriptionBoxId1, rating1, reviewText1);

        when(reviewService.createReview(any(Review.class))).thenReturn(review);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("customerId", customerId1);
        requestBody.put("review", reviewText1);
        requestBody.put("rating", String.valueOf(rating1));

        MvcResult mvcResult = mockMvc.perform(post("/review/" + subscriptionBoxId1 + "/create-review")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetAllReview() throws Exception {
        List<Review> reviews = new ArrayList<>();

        String customerId1 = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
        String subscriptionBoxId1 = "99963276-4e60-4e9a-96ce-8d5a9957209d";
        int rating1 = 4;
        String reviewText1 = "Great snacks!";

        Review review = new Review(customerId1, subscriptionBoxId1, rating1, reviewText1);
        reviews.add(review);

        when(reviewService.findAllReviews()).thenReturn(reviews);
    
        MvcResult mvcResult = mockMvc.perform(get("/review/reviews")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    
        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetReviewById() throws Exception {
        String customerId1 = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
        String subscriptionBoxId1 = "99963276-4e60-4e9a-96ce-8d5a9957209d";
        int rating1 = 4;
        String reviewText1 = "Great snacks!";

        Review review = new Review(customerId1, subscriptionBoxId1, rating1, reviewText1);
        review.setIdReview("review1");

        Optional<Review> optionalReview = Optional.of(review);

        when(reviewService.findReviewById(review.getIdReview())).thenReturn(optionalReview);

        MvcResult mvcResult = mockMvc.perform(get("/review/review1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

     @Test
    public void testGetReviewByUserId() throws Exception {
        List<Review> reviews = new ArrayList<>();
        
        String userId = "1";
        String subscriptionBoxId = "99963276-4e60-4e9a-96ce-8d5a9957209d";
        int rating = 4;
        String reviewText = "Great snacks!";
    
        Review review = new Review(userId, subscriptionBoxId, rating, reviewText);
        reviews.add(review);

        when(reviewService.findAllByUserId(userId)).thenReturn(reviews);

        MvcResult mvcResult = mockMvc.perform(get("/review/user/" + userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetReviewBySubscriptionBoxId() throws Exception {
        List<Review> reviews = new ArrayList<>();

        String userId = "1";
        String subscriptionBoxId = "99963276-4e60-4e9a-96ce-8d5a9957209d";
        int rating = 4;
        String reviewText = "Great snacks!";

        Review review = new Review(userId,subscriptionBoxId,rating,reviewText);
        reviews.add(review);

        when(reviewService.findAllBySubscriptionBoxId(subscriptionBoxId)).thenReturn(reviews);

        MvcResult mvcResult = mockMvc.perform(get("/review/subscription-box/" + subscriptionBoxId)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testUpdateReviewStatus() throws Exception {
        String customerId1 = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
        String subscriptionBoxId1 = "99963276-4e60-4e9a-96ce-8d5a9957209d";
        int rating1 = 4;
        String reviewText1 = "Great snacks!";

        Review review = new Review(customerId1, subscriptionBoxId1, rating1, reviewText1);
        review.setIdReview("review1");

        when(reviewService.updateReviewStatus(anyString(), anyString())).thenReturn(review);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("status", "APPROVED");

        MvcResult mvcResult = mockMvc.perform(patch("/review/review1/change-status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testDeleteReview() throws Exception {
        String customerId1 = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
        String subscriptionBoxId1 = "99963276-4e60-4e9a-96ce-8d5a9957209d";
        int rating1 = 4;
        String reviewText1 = "Great snacks!";

        Review review = new Review(customerId1, subscriptionBoxId1, rating1, reviewText1);
        review.setIdReview("review1");

        doNothing().when(reviewService).deleteReview(anyString());

        MvcResult mvcResult = mockMvc.perform(delete("/review/" + review.getIdReview() + "/delete-review")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testUpdateReview() throws Exception {
        String customerId1 = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
        String subscriptionBoxId1 = "99963276-4e60-4e9a-96ce-8d5a9957209d";
        int rating1 = 4;
        String reviewText1 = "Great snacks!";
        String reviewId = "review1";

        Review existingReview = new Review(customerId1, subscriptionBoxId1, rating1, reviewText1);
        existingReview.setIdReview(reviewId);

        String updatedReviewText = "Updated review text";
        int updatedRating = 5;

        Review updatedReview = new Review(customerId1, subscriptionBoxId1, updatedRating, updatedReviewText);
        updatedReview.setIdReview(reviewId);

        when(reviewService.findReviewById(reviewId)).thenReturn(Optional.of(existingReview));
        when(reviewService.updateReview(any(Review.class))).thenReturn(updatedReview);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("review", updatedReviewText);
        requestBody.put("rating", String.valueOf(updatedRating));

        MvcResult mvcResult = mockMvc.perform(patch("/review/" + reviewId + "/update-review")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testFilterReviewsBySubscriptionBoxIdAndRating() throws Exception {
        List<Review> reviews = new ArrayList<>();

        String userId = "1";
        String subscriptionBoxId = "99963276-4e60-4e9a-96ce-8d5a9957209d";
        int rating = 4;
        String reviewText = "Great snacks!";

        Review review = new Review(userId, subscriptionBoxId, rating, reviewText);
        reviews.add(review);

        when(reviewService.findAllByRatingAndSubscriptionBoxId(anyInt(), anyString())).thenReturn(reviews);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("rating", String.valueOf(rating));

        MvcResult mvcResult = mockMvc.perform(get("/review/subscription-box/" + subscriptionBoxId + "/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

     @Test
    public void testGetReviewByIdNotFound() throws Exception {
        String reviewId = "review1";

        when(reviewService.findReviewById(reviewId)).thenReturn(Optional.empty());

        MvcResult mvcResult = mockMvc.perform(get("/review/" + reviewId)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testUpdateReviewNotFound() throws Exception {
        String reviewId = "review1";

        when(reviewService.findReviewById(reviewId)).thenReturn(Optional.empty());

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("review", "Updated review text");
        requestBody.put("rating", "5");

        MvcResult mvcResult = mockMvc.perform(patch("/review/" + reviewId + "/update-review")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
}
