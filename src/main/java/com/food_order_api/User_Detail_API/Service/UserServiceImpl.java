package com.food_order_api.User_Detail_API.Service;

import com.food_order_api.User_Detail_API.Dto.UserDto;
import com.food_order_api.User_Detail_API.Entity.User;
import com.food_order_api.User_Detail_API.Mapper.UserMapper;
import com.food_order_api.User_Detail_API.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    public UserDto addUser(UserDto userDTO) {
        User savedUser = userRepository.save(UserMapper.INSTANCE.mapUserDTOToUser(userDTO));
        return UserMapper.INSTANCE.mapUserToUserDTO(savedUser);

    }

    public ResponseEntity<UserDto> fetchUserDetailsById(Integer userId) {
        Optional<User> fetchedUser =  userRepository.findById(userId);
        if(fetchedUser.isPresent())
            return new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDTO(fetchedUser.get()), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
