package com.food_order_api.User_Detail_API.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto
{
    private int userId;
    private String userName;
    private String userPassword;
    private String userAddress;
    private String userCity;
}
