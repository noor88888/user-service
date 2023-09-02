package com.food_order_api.User_Detail_API.Service;

import com.food_order_api.User_Detail_API.Dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    UserDto addUser(UserDto userDTO);

    ResponseEntity<UserDto> fetchUserDetailsById(Integer userId);
}
