package bookReviewer.business.mapper;

import bookReviewer.business.model.RatingBusiness;
import bookReviewer.persistence.model.Rating;

import java.util.ArrayList;
import java.util.List;

public final class RatingBusinessMapper {
    public static Rating rating(RatingBusiness ratingBusiness){
        Rating rating = new Rating();
        rating.setId(ratingBusiness.getId());
        rating.setScore(ratingBusiness.getScore());
        rating.setTitle(ratingBusiness.getTitle());
        rating.setContent(ratingBusiness.getContent());
        rating.setUserId(ratingBusiness.getUserId());
        rating.setAuthor(ratingBusiness.getAuthor());
        rating.setBook(BookBusinessMapper.book(ratingBusiness.getBook()));
        return rating;
    }


    public static RatingBusiness ratingBusiness(Rating rating){
        RatingBusiness ratingBusiness = new RatingBusiness();
        ratingBusiness.setId(rating.getId());
        ratingBusiness.setScore(rating.getScore());
        ratingBusiness.setContent(rating.getContent());
        ratingBusiness.setTitle(rating.getTitle());
        ratingBusiness.setUserId(rating.getUserId());
        ratingBusiness.setAuthor(rating.getAuthor());
        ratingBusiness.setBook(BookBusinessMapper.bookBusiness(rating.getBook()));
        return ratingBusiness;

    }

    public static ArrayList<RatingBusiness> ratingBusinessList(List<Rating> ratingList){
        ArrayList<RatingBusiness> ratingBusinessArrayList = new ArrayList<>();
        for (Rating rating: ratingList) {
            ratingBusinessArrayList.add(ratingBusiness(rating));
        }
        return ratingBusinessArrayList;
    }

    public static ArrayList<Rating> ratingList(List<RatingBusiness> ratingBusinessList){
        ArrayList<Rating> ratingArrayList = new ArrayList<>();
        for (RatingBusiness ratingBusiness: ratingBusinessList) {
            ratingArrayList.add(rating(ratingBusiness));
        }
        return ratingArrayList;
    }
}
