package com.hotelservice.services;

import com.hotelservice.entities.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {

    Hotel save(Hotel hotel);

    Hotel getHotel(String id);

    List<Hotel>getAllHotels();

    Hotel updateHotel(String id,Hotel hotel);

    Hotel deleteHotel(String id);



}
