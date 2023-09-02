package com.food_order_api.User_Detail_API.Controller;

import com.food_order_api.User_Detail_API.Dto.UserDto;
import com.food_order_api.User_Detail_API.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDTO) {
        UserDto savedUser = userService.addUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/fetchUserById/{userId}")
    public ResponseEntity<UserDto> fetchUserDetailsById(@PathVariable Integer userId) {
        return userService.fetchUserDetailsById(userId);
    }
}