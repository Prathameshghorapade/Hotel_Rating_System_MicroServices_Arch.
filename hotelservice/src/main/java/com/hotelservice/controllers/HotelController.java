package com.hotelservice.controllers;

import com.hotelservice.entities.Hotel;
import com.hotelservice.services.HotelServiceImpl;
import org.hibernate.annotations.Fetch;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hotel")
public class HotelController {

    private final HotelServiceImpl hotelService;

    public HotelController(HotelServiceImpl hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public ResponseEntity<List<Hotel>>getAllHotels(){

       List<Hotel>hotels =hotelService.getAllHotels();

       return new ResponseEntity<>(hotels, HttpStatus.FOUND);

    }

    @GetMapping("{id}")
    public ResponseEntity<Hotel>getHotelByID(@PathVariable String id){

      Hotel hotel = hotelService.getHotel(id);

      return new ResponseEntity<>(hotel,HttpStatus.FOUND);
    }


    @PostMapping
    public ResponseEntity<Hotel>saveHotel(@RequestBody Hotel hotel){

        Hotel hotel1=hotelService.save(hotel);

        return new ResponseEntity<>(hotel,HttpStatus.CREATED);

    }

    @PutMapping("{id}")
    public ResponseEntity<Hotel>updateHotel(@PathVariable String id,@RequestBody Hotel hotel){

       Hotel existingHotel =hotelService.updateHotel(id,hotel);

       if (existingHotel ==null){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

      Hotel updatedHotel =hotelService.updateHotel(id,existingHotel);

       return new ResponseEntity<>(updatedHotel,HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Hotel>deleteHotel(@PathVariable String id){

      Hotel deletedHotel = hotelService.deleteHotel(id);
        return new ResponseEntity<>(deletedHotel,HttpStatus.OK);
    }







}
