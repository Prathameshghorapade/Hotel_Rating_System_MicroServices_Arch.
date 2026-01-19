package com.ratingservice.controllers;

import com.ratingservice.entities.Rating;
import com.ratingservice.services.RatingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ratings")
public class RatingController {

   private final RatingServiceImpl ratingService;

    public RatingController(RatingServiceImpl ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<Rating>createRating(@RequestBody Rating rating){

      Rating rating1 = ratingService.savRating(rating);

      return new ResponseEntity<>(rating1,HttpStatus.CREATED);

    }


    @GetMapping
    public ResponseEntity<List<Rating>>getAllRatings(){

      List<Rating>allRatings = ratingService.getAllRatings();

      return new ResponseEntity<>(allRatings, HttpStatus.FOUND);

    }

    @GetMapping("userId/{userId}")
    public ResponseEntity<List<Rating>>getAllRatingsByUserID(@PathVariable String  userId){

      List<Rating>allRatings = ratingService.getRatingByUserID(userId);

      return new ResponseEntity<>(allRatings,HttpStatus.FOUND);

    }

    @GetMapping("hotelId/{hotelId}")
    public ResponseEntity<List<Rating>>getAllRatingsByHotelId(@PathVariable String hotelId){

        List<Rating>allRatings=ratingService.getRatingByHotelId(hotelId);

        return new ResponseEntity<>(allRatings,HttpStatus.FOUND);

    }

    @DeleteMapping("{ratingId}")
    public void deleteRatings(@PathVariable String ratingId){

        ratingService.deleteRating(ratingId);

    }




}
