package com.vn.microservice.users.service;

import com.vn.microservice.users.dto.UserDto;

public interface UserService {

    public UserDto createUser(UserDto userDto);
}
