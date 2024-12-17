package com.projects.rating.service.RatingService.services;

import com.projects.rating.service.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    public Rating createRating(Rating rating);

    public Rating getRatingById(String id);

    public List<Rating> getAllRatings();

    public List<Rating> getRatingByUserId(String userId);

    public Rating getRatingByHotelId(String hotelId);
}
