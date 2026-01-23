package com.userservice.services;

import com.userservice.entities.Hotel;
import com.userservice.entities.Rating;
import com.userservice.entities.User;
import com.userservice.exceptions.ResourceNotFoundException;
import com.userservice.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

   private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }






    @Override
    public User saveUser(User user) {

       String randomUserID =UUID.randomUUID().toString();
       user.setId(randomUserID);

       return userRepository.save(user);

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users;
    }

    @Override
    public User getUser(String id) {

        // 1️⃣ Fetch the user from DB
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User With This Id Not Found: " + id));

      Rating[]ratings = restTemplate.getForObject("http://localhost:8029/ratings/userId/"+foundUser.getId(),Rating[].class);

        assert ratings != null;
        foundUser.setRatings(List.of(ratings));

       List<Rating>ratingList =Arrays.stream(ratings).toList();

       ratingList.stream().map(rating -> {

          ResponseEntity<Hotel>forObject =restTemplate.getForEntity("http://localhost:8028/hotel/"+rating.getHotelId(),Hotel.class);

          Hotel hotel=forObject.getBody();

          rating.setHotel(hotel);

          return rating;

       }).collect(Collectors.toList());

       foundUser.setRatings(ratingList);

        return foundUser;
    }



    @Override
    public void deleteUser(String id) {

        User foundUser=userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not Found With this Id"+id));
        userRepository.delete(foundUser);

    }

    @Override
    public User updateUSer(String id, User user) {

     User existingUser  = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User Not Found"+id));

     existingUser.setName(user.getName());
     existingUser.setEmail(user.getEmail());
     existingUser.setAbout(user.getAbout());

        return userRepository.save(existingUser);
    }
}
