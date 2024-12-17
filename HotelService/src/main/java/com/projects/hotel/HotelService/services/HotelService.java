package com.projects.hotel.HotelService.services;

import com.projects.hotel.HotelService.entities.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {

    public Hotel createHotel(Hotel hotel);

    public Hotel updateHotel(Hotel hotel);

    public Hotel deleteHotel(Hotel hotel);

    public Hotel getHotelById(String id);

    public List<Hotel> getAllHotels();
}
