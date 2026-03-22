package com.example.userService.UserService;

import com.example.userService.Dao.UserRepository;
import com.example.userService.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;


    public User getUser(Long id) {
        return userRepo.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
    }


    public User createUser(User user) {
        return userRepo.save(user);
    }


    public void deleteUser(Long id) {
        if(!userRepo.existsById(id))
        {
           throw new RuntimeException("User Not Found");
        }
        userRepo.deleteById(id);
    }
}
