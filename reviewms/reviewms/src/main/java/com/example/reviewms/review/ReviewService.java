package com.example.reviewms.review;



import java.util.List;

public interface ReviewService {
    List<Review> getallreviews(Long companyId);
    boolean addreview(Long companyId,Review review);
    Review getreview(Long reviewid);
    boolean updatereview(long reviewid,Review updatedreview);
    boolean deletereview(long reviewid);

}
