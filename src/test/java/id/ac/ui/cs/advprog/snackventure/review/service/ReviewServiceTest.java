package id.ac.ui.cs.advprog.snackventure.review.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.snackventure.review.enums.ReviewStatus;
import id.ac.ui.cs.advprog.snackventure.review.model.Review;
import id.ac.ui.cs.advprog.snackventure.review.repository.ReviewRepository;


@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {
    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReview(){
        String customerId1 = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
        String subscriptionBoxId1 = "99963276-4e60-4e9a-96ce-8d5a9957209d";
        int rating1 = 4;
        String reviewText1 = "Great snacks!";

        Review review = new Review(customerId1, subscriptionBoxId1, rating1, reviewText1);
        
        when(reviewRepository.save(any(Review.class))).thenReturn(review);
        Review createReview = reviewService.createReview(review);
        assertNotNull(createReview);
        assertEquals(review.getIdReview(), createReview.getIdReview());
    }

    @Test
    void testFindAllReviews(){
        Review review1 = new Review();
        Review review2 = new Review();
        Review review3 = new Review();
        when(reviewRepository.findAll()).thenReturn(Arrays.asList(review1,review2,review3));
        
        List<Review> reviewList = reviewService.findAllReviews();
        assertTrue(reviewList.size()!=0);
        assertEquals(reviewList.size(), 3);
    }

    @Test
    void testFindAllReviewByUserId(){
        Review review1 = new Review();
        review1.setIdReview("07f9b8b0-7257-4434-a5b9-79c9703f0760");
        Review review2 = new Review();
        review2.setIdReview("07f9b8b0-7257-4434-a5b9-79c9703f0760");
        when(reviewRepository.findAllByUserId("07f9b8b0-7257-4434-a5b9-79c9703f0760")).thenReturn(Arrays.asList(review1,review2));

        List<Review> reviews = reviewService.findAllByUserId("07f9b8b0-7257-4434-a5b9-79c9703f0760");
        assertTrue(reviews.size()!=0);
        assertEquals(reviews.size(), 2);
        assertEquals(reviews.get(0).getIdReview(), review1.getIdReview());
        assertEquals(reviews.get(1).getIdReview(), review2.getIdReview());
    }

    @Test
    void testFindReviewBySubscriptionBoxId(){
        Review review1 = new Review();
        review1.setSubscriptionBoxId("99963276-4e60-4e9a-96ce-8d5a9957209d");
        Review review2 = new Review();
        review2.setSubscriptionBoxId("99963276-4e60-4e9a-96ce-8d5a9957209d");

        when(reviewRepository.findAllBySubscriptionBoxId("99963276-4e60-4e9a-96ce-8d5a9957209d"))
        .thenReturn(Arrays.asList(review1,review2));
        
        List<Review> reviews = reviewService.findAllBySubscriptionBoxId("99963276-4e60-4e9a-96ce-8d5a9957209d");
        assertTrue(reviews.size()!=0);
        assertEquals(reviews.size(), 2);
        assertEquals(reviews.get(0).getSubscriptionBoxId(), review1.getSubscriptionBoxId());
        assertEquals(reviews.get(1).getSubscriptionBoxId(), review2.getSubscriptionBoxId());
    }

    @Test
    void testFindReviewById(){
        Review review = new Review();
        String reviewId = UUID.randomUUID().toString();
        review.setIdReview(reviewId);

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

        Optional<Review> result = reviewService.findReviewById(reviewId);
    
        assertTrue(result.isPresent());
        assertEquals(reviewId, result.get().getIdReview());
    }

    @Test
    void testEditReview(){
        String customerId1 = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
        String subscriptionBoxId1 = "99963276-4e60-4e9a-96ce-8d5a9957209d";
        int rating1 = 4;
        String reviewText1 = "Great snacks!";

        Review review = new Review(customerId1, subscriptionBoxId1, rating1, reviewText1);
        when(reviewRepository.save(review)).thenReturn(review);

        review.setReview("oke mantap sekali ini");
        review.setRating(5);
        Review editedReview = reviewService.updateReview(review);

        assertNotNull(editedReview);
        assertEquals(review.getIdReview(),editedReview.getIdReview());
        assertEquals(editedReview.getReview(),"oke mantap sekali ini");
        assertEquals(editedReview.getRating(), 5);
    }

    @Test
    void testDeleteReview(){
        String userId = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
        String subscriptionBoxId1 = "99963276-4e60-4e9a-96ce-8d5a9957209d";
        int rating1 = 4;
        String reviewText1 = "Great snacks!";

        Review review = new Review(userId, subscriptionBoxId1, rating1, reviewText1);

        doNothing().when(reviewRepository).deleteById(review.getIdReview());
        assertDoesNotThrow(() -> reviewService.deleteReview(review.getIdReview()));
        verify(reviewRepository).deleteById(review.getIdReview());
    }

    @Test
    void testFindByIdThrowsException() {
        when(reviewRepository.findById("not-exist")).thenReturn(Optional.empty());
        Optional<Review> result = reviewService.findReviewById("not-exist");
        assertFalse(result.isPresent());
    }

    @Test
    void testFilterReviewByRating(){
        String userId = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
        String subscriptionBoxId1 = "99963276-4e60-4e9a-96ce-8d5a9957209d";
        int rating1 = 4;
        String reviewText1 = "Great snacks!";

        Review review = new Review(userId, subscriptionBoxId1, rating1, reviewText1);

        when(reviewRepository.findFilteredReviewByRating(4, "99963276-4e60-4e9a-96ce-8d5a9957209d")).thenReturn(Arrays.asList(review));
        List<Review> reviews = reviewService.findFilteredReviewByRating(4, "99963276-4e60-4e9a-96ce-8d5a9957209d");
        assertTrue(reviews.size()!=0);
        assertEquals(reviews.size(), 1);
        assertEquals(reviews.get(0).getIdReview(), review.getIdReview());
    }
    
    @Test
    public void testUpdateReviewStatusToApproved() {
        Review review = new Review();
        UUID id = UUID.randomUUID();
        review.setIdReview(id.toString());

        when(reviewService.findReviewById(id.toString())).thenReturn(Optional.of(review));

        Review updatedReview = reviewService.updateReviewStatus(id.toString(), ReviewStatus.APPROVED.toString());

        verify(reviewRepository, times(1)).findById(id.toString());
        verify(reviewRepository, times(1)).save(review);
        assertEquals(ReviewStatus.APPROVED, updatedReview.getReviewStatus());
    }

    @Test
    public void testUpdateReviewStatusToRejected() {
        Review review = new Review();
        UUID id = UUID.randomUUID();
        review.setIdReview(id.toString());

        when(reviewService.findReviewById(id.toString())).thenReturn(Optional.of(review));

        Review updatedReview = reviewService.updateReviewStatus(id.toString(), ReviewStatus.REJECTED.toString());

        verify(reviewRepository, times(1)).findById(id.toString());
        verify(reviewRepository, times(1)).save(review);
        assertEquals(ReviewStatus.REJECTED, updatedReview.getReviewStatus());
    }

}
