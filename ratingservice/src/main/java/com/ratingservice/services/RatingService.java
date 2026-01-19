package com.ratingservice.services;


import com.ratingservice.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating savRating(Rating rating);

    List<Rating> getAllRatings();

    List<Rating> getRatingByUserID(String userId);

    List<Rating> getRatingByHotelId(String hotelId);

    void deleteRating(String ratingId);




}
