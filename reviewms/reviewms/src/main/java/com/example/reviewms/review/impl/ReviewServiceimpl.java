package com.example.reviewms.review.impl;


import com.example.reviewms.review.Review;
import com.example.reviewms.review.ReviewRepository;
import com.example.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceimpl implements ReviewService {
    private final ReviewRepository reviewRespository;
   ;

    public ReviewServiceimpl(ReviewRepository reviewRespository) {
        this.reviewRespository = reviewRespository;

    }

    @Override
    public List<Review> getallreviews(Long companyId) {
        List<Review>reviews=reviewRespository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addreview(Long companyId, Review review) {

         if(companyId!=null){
             review.setCompanyId(companyId);
             reviewRespository.save(review);
             return true;
         }
         else {
             return false;
         }

    }

    @Override
    public Review getreview(Long reviewid) {
       return reviewRespository.findById(reviewid).orElse(null);

    }

    @Override
    public boolean updatereview(long reviewid, Review updatedreview) {
        Review review=reviewRespository.findById(reviewid).orElse(null);
        if(review!=null){
            review.setCompanyId(updatedreview.getCompanyId());
            review.setDescription(updatedreview.getDescription());
            review.setTitle(updatedreview.getTitle());
            review.setRating(updatedreview.getRating());
            reviewRespository.save(review);
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public boolean deletereview(long reviewid) {
        Review review=reviewRespository.findById(reviewid).orElse(null);
        if(review!=null){
            reviewRespository.delete(review);
            return true;

        } else {

            return false;
        }

    }
}
