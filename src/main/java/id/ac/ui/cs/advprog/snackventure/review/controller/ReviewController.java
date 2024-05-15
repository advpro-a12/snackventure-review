package id.ac.ui.cs.advprog.snackventure.review.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    @GetMapping("/")
    public String review(){
        return "Hello This Is Review!";
    }
}