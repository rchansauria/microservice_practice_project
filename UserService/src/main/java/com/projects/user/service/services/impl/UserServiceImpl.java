package com.projects.user.service.services.impl;

import com.projects.user.service.entities.Hotel;
import com.projects.user.service.entities.Rating;
import com.projects.user.service.entities.User;
import com.projects.user.service.exceptions.ResourceNotFoundException;
import com.projects.user.service.external.services.HotelService;
import com.projects.user.service.external.services.RatingService;
import com.projects.user.service.repositories.UserRepository;
import com.projects.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User createUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        // Rating[] ratings = restTemplate.getForObject("http://RATINGSERVICE/ratings/user/" + user.getUserId(), Rating[].class);
        /* We will be implementing Feign client for fetching the data  */
        List<Rating> ratings = ratingService.getRatingByUserId(user.getUserId()).getBody();
        List<Rating> ratingNewList = ratings.stream().map(rating -> {
           // ResponseEntity<Hotel> hotel =  restTemplate.getForEntity("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            /* We will be implementing Feign client for fetching the data  */
           ResponseEntity<Hotel> hotel =  hotelService.getHotelById(rating.getHotelId());
           rating.setHotel(hotel.getBody());
           return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingNewList);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
