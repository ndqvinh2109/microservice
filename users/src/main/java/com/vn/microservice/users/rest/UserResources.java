package com.vn.microservice.users.rest;

import com.vn.microservice.users.dto.UserDto;
import com.vn.microservice.users.model.CreateUserRequestModel;
import com.vn.microservice.users.model.CreateUserResponseModel;
import com.vn.microservice.users.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserResources {

    @Autowired
    private Environment env;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody CreateUserRequestModel createUserRequestModel) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(createUserRequestModel, UserDto.class);
        UserDto returnedUserDto = userService.createUser(userDto);

        CreateUserResponseModel createUserResponseModel = modelMapper.map(returnedUserDto, CreateUserResponseModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(createUserResponseModel);
    }

}
