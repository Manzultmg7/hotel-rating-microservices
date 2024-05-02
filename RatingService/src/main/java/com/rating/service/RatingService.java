package com.rating.service;

import com.rating.entity.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating(Rating rating);

    List<Rating> getAllRatings();

    List<Rating> getRatingsByUserId(Long userId);

    List<Rating> getRatingsByHotelId(Long hotelId);


}
