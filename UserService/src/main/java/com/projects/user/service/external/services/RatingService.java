package com.projects.user.service.external.services;

import com.projects.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    @GetMapping("/ratings/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId);
}
