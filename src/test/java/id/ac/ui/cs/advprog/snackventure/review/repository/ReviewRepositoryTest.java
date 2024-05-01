package id.ac.ui.cs.advprog.snackventure.review.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.snackventure.review.model.Review;

@ExtendWith(MockitoExtension.class)
public class ReviewRepositoryTest {
    
    @InjectMocks
    private ReviewRepository reviewRepository;
    private Review review1;
    private Review review2;

    @BeforeEach
    void setup(){
        String idReview1 = "4ec1f12c-8e2b-4f02-85c0-531e762b483b";
        String customerId1 = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
        String subscriptionBoxId1 = "99963276-4e60-4e9a-96ce-8d5a9957209d";
        int rating1 = 4;
        String reviewText1 = "Great snacks!";

        review1 = new Review(idReview1, customerId1, subscriptionBoxId1, rating1, reviewText1);

        String idReview2 = "8d3e2b3b-b0d9-4e80-a641-2e02c1a935fb";
        String customerId2 = "07f9b8b0-7257-4434-a5b9-79c9703f0760";
        String subscriptionBoxId2 = "1b1e7a6c-c8f5-48bb-bd07-41b7b2c8af4c";
        int rating2 = 3;
        String reviewText2 = "Great snacks!";
        
        review2 = new Review(idReview2, customerId2, subscriptionBoxId2, rating2, reviewText2);
    }

    @Test
    void testCreateReview(){
        Review savedReview1 = reviewRepository.create(review1);
        assertNotNull(savedReview1.getIdReview());
        assertEquals(review1,savedReview1);

        Review savedReview2 = reviewRepository.create(review2);
        assertNotNull(savedReview2.getIdReview());
        assertEquals(review2,savedReview2);
    }

    @Test
    void testFindAllReview(){
        reviewRepository.create(review1);
        reviewRepository.create(review2);
        List<Review> allReview = reviewRepository.findAll();
        assertFalse(allReview.isEmpty());
        assertEquals(2, allReview.size());
    }

    @Test 
    void testReviewFindById(){
        Review savedReview = reviewRepository.create(review1);
        Review findSavedReview = reviewRepository.findById(savedReview.getIdReview());
        assertEquals(savedReview, findSavedReview);
    }

    @Test
    void testFindAllByUserId(){
        reviewRepository.create(review1);
        assertFalse(reviewRepository.findAllByUserId(review1.getIdReview()).isEmpty());
    }

    @Test
    void testFindAllBySubscriptionBoxId(){
        reviewRepository.create(review1);
        reviewRepository.create(review2);
        assertFalse(reviewRepository.findAllBySubscriptionId("99963276-4e60-4e9a-96ce-8d5a9957209d").isEmpty());
    }

    @Test
    void TestFilterByRating(){
        reviewRepository.create(review1);
        reviewRepository.create(review2);
        assertFalse(reviewRepository.findAllFilteredReviewByRating("99963276-4e60-4e9a-96ce-8d5a9957209d", 4).isEmpty());
    }

    @Test
    void testEditReview() {
        Review savedReview2 = reviewRepository.create(review2);
    
        Review reviewToEdit = reviewRepository.findById("8d3e2b3b-b0d9-4e80-a641-2e02c1a935fb");
        
        reviewToEdit.setRating(5); 
        reviewToEdit.setReview("This snack box is amazing!"); 
    
        Review editedReview = reviewRepository.edit(reviewToEdit);
    
        assertEquals(5, editedReview.getRating());
        assertEquals("This snack box is amazing!", editedReview.getReview());
        assertEquals(reviewToEdit, editedReview);
    
        assertEquals(review2, reviewRepository.findById(savedReview2.getIdReview()));
    }

    @Test
    void testDeleteReview() {
        Review savedReview = reviewRepository.create(review1);
        Review deletedReview = reviewRepository.delete(savedReview.getIdReview());

        assertEquals(savedReview, deletedReview);
        assertEquals(0, reviewRepository.findAll().size()); 
}



}
