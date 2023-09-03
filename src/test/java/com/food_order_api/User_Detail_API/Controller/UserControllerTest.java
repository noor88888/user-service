package com.food_order_api.User_Detail_API.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.food_order_api.User_Detail_API.Dto.UserDto;
import com.food_order_api.User_Detail_API.Service.UserService;

public class UserControllerTest {
	
	 @InjectMocks
	    private UserController userController;

	    @Mock
	    private UserService userService;

	    @BeforeEach
	    public void setup() {
	        MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    public void testAddUser() {
	        UserDto userDto = new UserDto();
	        userDto.setUserId(1);
	        userDto.setUserName("testUser");

	        when(userService.addUser(any(UserDto.class))).thenReturn(userDto);

	        ResponseEntity<UserDto> response = userController.addUser(userDto);

	        verify(userService, times(1)).addUser(userDto);

	        assert response.getStatusCode() == HttpStatus.CREATED;
	        assert response.getBody() != null;
	        assert response.getBody().getUserId() == 1;
	        assert response.getBody().getUserName().equals("testUser");
	    }

	    @Test
	    public void testFetchUserDetailsById() {
	        int userId = 1;
	        UserDto userDto = new UserDto();
	        userDto.setUserId(userId);
	        userDto.setUserName("testUser");

	        when(userService.fetchUserDetailsById(userId)).thenReturn(new ResponseEntity<>(userDto, HttpStatus.OK));

	        ResponseEntity<UserDto> response = userController.fetchUserDetailsById(userId);

	        verify(userService, times(1)).fetchUserDetailsById(userId);

	        assert response.getStatusCode() == HttpStatus.OK;
	        assert response.getBody() != null;
	        assert response.getBody().getUserId() == userId;
	        assert response.getBody().getUserName().equals("testUser");
	    }

	    @Test
	    public void testFetchUserDetailsByIdNotFound() {
	        int userId = 1;

	        when(userService.fetchUserDetailsById(userId)).thenReturn(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));

	        ResponseEntity<UserDto> response = userController.fetchUserDetailsById(userId);

	        verify(userService, times(1)).fetchUserDetailsById(userId);

	        assert response.getStatusCode() == HttpStatus.NOT_FOUND;
	        assert response.getBody() == null;
	    }

}
