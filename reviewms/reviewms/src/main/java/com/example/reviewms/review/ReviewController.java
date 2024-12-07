package com.example.reviewms.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping
    public ResponseEntity<List<Review>> getallreviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getallreviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addreview(@RequestParam Long companyId,@RequestBody Review review){
        boolean isreviewupdate= reviewService.addreview(companyId,review);
        if(isreviewupdate){
            return new ResponseEntity<>("Review added successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Review not added",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{reviewid}")
    public ResponseEntity<Review>getreview(@PathVariable Long reviewid){
        return new ResponseEntity<>(reviewService.getreview(reviewid),HttpStatus.OK);
    }
    @PutMapping("/{reviewid}")
    public ResponseEntity<String> updatereview(@PathVariable long reviewid,@RequestBody Review review){

        boolean isreviewupdated= reviewService.updatereview(reviewid,review);
        if(isreviewupdated){
            return new ResponseEntity<>("review updated successfully",HttpStatus.OK);

        }
        else {
            return new ResponseEntity<>("review not updated",HttpStatus.OK);
        }
    }
    @DeleteMapping("/{reviewid}")
    public ResponseEntity<String> deletereview(@PathVariable long reviewid) {
        boolean isreviewdeleted = reviewService.deletereview(reviewid);
        if(isreviewdeleted){
            return new ResponseEntity<>("review deleted successfully",HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("review not deleted",HttpStatus.NOT_FOUND);
        }
    }
}
