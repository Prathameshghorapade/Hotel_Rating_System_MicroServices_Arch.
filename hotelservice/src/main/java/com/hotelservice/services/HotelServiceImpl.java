package com.hotelservice.services;

import com.hotelservice.entities.Hotel;
import com.hotelservice.exceptions.ResourceNotFoundException;
import com.hotelservice.repositories.HotelRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService{

    private final HotelRepo hotelRepo;

    public HotelServiceImpl(HotelRepo hotelRepo) {
        this.hotelRepo = hotelRepo;
    }




    @Override
    public Hotel save(Hotel hotel) {

      String hotelID = UUID.randomUUID().toString();
        hotel.setId(hotelID);
       return hotelRepo.save(hotel);
    }

    @Override
    public Hotel getHotel(String id) {

       return hotelRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel is Not Found Of this Id "+id));
    }

    @Override
    public List<Hotel> getAllHotels() {
       return hotelRepo.findAll();
    }

    @Override
    public Hotel updateHotel(String id, Hotel hotel) {

       Hotel foundHotel =hotelRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel is Not Found With This Id "+id));

       foundHotel.setName(hotel.getName());
       foundHotel.setLocation(hotel.getLocation());
       foundHotel.setAbout(hotel.getAbout());

      return hotelRepo.save(foundHotel);

    }

    @Override
    public Hotel deleteHotel(String id) {

        Hotel foundHotel=hotelRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel Not Found With This Id "+id));
        hotelRepo.delete(foundHotel);

        return foundHotel;
    }
}
