package com.ratingservice.services;

import com.ratingservice.entities.Rating;
import com.ratingservice.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService{

    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }


    @Override
    public Rating savRating(Rating rating) {

        String ratingId= UUID.randomUUID().toString();

        rating.setRatingId(ratingId);

        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserID(String userId) {
       return ratingRepository.findRatingByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findRatingByHotelId(hotelId);
    }

    @Override
    public void deleteRating(String ratingId) {

       ratingRepository.deleteById(ratingId);

    }
}
