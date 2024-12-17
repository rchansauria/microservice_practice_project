package com.projects.rating.service.RatingService.repositories;

import com.projects.rating.service.RatingService.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface RatingRepository extends MongoRepository<Rating, String> {

    public List<Rating> findByUserId(String userId);
    public Rating findByHotelId(String hotelId);
}
