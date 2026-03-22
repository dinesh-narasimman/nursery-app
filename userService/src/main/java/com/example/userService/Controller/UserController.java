package com.example.userService.Controller;

import com.example.userService.Model.User;
import com.example.userService.UserService.UserService;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {

        return new ResponseEntity<>(userService.createUser(user),HttpStatus.CREATED);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
        return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
    }
}
