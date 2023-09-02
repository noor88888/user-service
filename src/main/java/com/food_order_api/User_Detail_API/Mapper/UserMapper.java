package com.food_order_api.User_Detail_API.Mapper;

import com.food_order_api.User_Detail_API.Dto.UserDto;
import com.food_order_api.User_Detail_API.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper
{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapUserDTOToUser(UserDto userDTO);
    UserDto mapUserToUserDTO(User user);
}
