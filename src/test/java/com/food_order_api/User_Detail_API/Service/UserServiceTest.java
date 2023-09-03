package com.food_order_api.User_Detail_API.Service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.food_order_api.User_Detail_API.Dto.UserDto;
import com.food_order_api.User_Detail_API.Entity.User;
import com.food_order_api.User_Detail_API.Mapper.UserMapper;
import com.food_order_api.User_Detail_API.Repository.UserRepository;

public class UserServiceTest {
	@InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddUser() {
        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setUserName("testUser");

        User user = UserMapper.INSTANCE.mapUserDTOToUser(userDto);

        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDto savedUser = userService.addUser(userDto);

        verify(userRepository, times(1)).save(user);

        assert savedUser != null;
        assert savedUser.getUserId() == 1;
        assert savedUser.getUserName().equals("testUser");
    }

    @Test
    public void testFetchUserDetailsById() {
        int userId = 1;
        User user = new User();
        user.setUserId(userId);
        user.setUserName("testUser");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        ResponseEntity<UserDto> response = userService.fetchUserDetailsById(userId);

        verify(userRepository, times(1)).findById(userId);

        assert response.getStatusCode().equals(HttpStatus.OK);
        assert response.getBody() != null;
        assert response.getBody().getUserId() == userId;
        assert response.getBody().getUserName().equals("testUser");
    }

    @Test
    public void testFetchUserDetailsByIdNotFound() {
        int userId = 1;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResponseEntity<UserDto> response = userService.fetchUserDetailsById(userId);

        verify(userRepository, times(1)).findById(userId);

        assert response.getStatusCode().equals(HttpStatus.NOT_FOUND);
        assert response.getBody() == null;
    }

}
