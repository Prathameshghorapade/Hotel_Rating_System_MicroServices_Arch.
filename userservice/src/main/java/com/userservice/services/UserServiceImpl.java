package com.userservice.services;

import com.userservice.entities.User;
import com.userservice.exceptions.ResourceNotFoundException;
import com.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

   private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User saveUser(User user) {

       String randomUserID =UUID.randomUUID().toString();
       user.setId(randomUserID);

       return userRepository.save(user);

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User With This Id is Not Found"+id));

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
