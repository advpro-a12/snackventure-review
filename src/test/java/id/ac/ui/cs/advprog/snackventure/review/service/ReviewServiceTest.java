package id.ac.ui.cs.advprog.snackventure.review.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.snackventure.review.model.Review;
import id.ac.ui.cs.advprog.snackventure.review.repository.ReviewRepository;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {
    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private Review review;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        String idreview = "4ec1f12c-8e2b-4f02-85c0-531e762b483b";
        String customerId1 = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
        String subscriptionBoxId1 = "99963276-4e60-4e9a-96ce-8d5a9957209d";
        int rating1 = 4;
        String reviewText1 = "Great snacks!";

        review = new Review(idreview, customerId1, subscriptionBoxId1, rating1, reviewText1);
    }

    @Test
    void testCreateReview(){
        when(reviewRepository.create(review)).thenReturn(review);
        Review createReview = reviewService.createReview(review);
        assertNotNull(createReview);
        assertEquals(review.getIdReview(), createReview.getIdReview());
    }

    @Test
    void testFindAllReviews(){
        when(reviewRepository.findAll()).thenReturn(Arrays.asList(review));
        List<Review> reviewList = reviewService.findAllReviews();
        assertTrue(reviewList.size()!=0);
        assertEquals(reviewList.size(), 1);
        assertEquals(reviewList.get(0).getIdReview(), review.getIdReview());
    }

    @Test
    void testFindAllReviewByUserId(){
        when(reviewRepository.findAllByUserId("07f9b8b0-7257-4434-a5b9-79c9703f0760")).thenReturn(Arrays.asList(review));
        List<Review> reviews = reviewService.findAllByUserId("07f9b8b0-7257-4434-a5b9-79c9703f0760");
        assertTrue(reviews.size()!=0);
        assertEquals(reviews.size(), 1);
        assertEquals(reviews.get(0).getIdReview(), review.getIdReview());
    }

    @Test
    void testFindReviewBySubscriptionBoxId(){
        when(reviewRepository.findAllBySubscriptionId("99963276-4e60-4e9a-96ce-8d5a9957209d")).thenReturn(Arrays.asList(review));
        List<Review> reviews = reviewService.findAllBySubscriptionBoxId("99963276-4e60-4e9a-96ce-8d5a9957209d");
        assertTrue(reviews.size()!=0);
        assertEquals(reviews.size(), 1);
        assertEquals(reviews.get(0).getIdReview(), review.getIdReview());
    }

    @Test
    void testEditReview(){
        when(reviewRepository.edit(review)).thenReturn(review);
        Review editedReview = reviewService.updateReview(review);
        assertNotNull(editedReview);
        assertEquals(review.getIdReview(),editedReview.getIdReview());
    }

    @Test
    void testDeleteReview(){
        when(reviewRepository.delete("4ec1f12c-8e2b-4f02-85c0-531e762b483b")).thenReturn(review);
        Review deletedReview = reviewService.deleteReview("4ec1f12c-8e2b-4f02-85c0-531e762b483b");
        assertNotNull(deletedReview);
        assertEquals(review.getIdReview(), deletedReview.getIdReview());
    }

    @Test
    void testFilterReviewByRating(){
        when(reviewRepository.findAllFilteredReviewByRating("99963276-4e60-4e9a-96ce-8d5a9957209d", 4)).thenReturn(Arrays.asList(review));
        List<Review> reviews = reviewService.findFilteredReviewByRating("99963276-4e60-4e9a-96ce-8d5a9957209d", 4);
        assertTrue(reviews.size()!=0);
        assertEquals(reviews.size(), 1);
        assertEquals(reviews.get(0).getIdReview(), review.getIdReview());
    }

}
