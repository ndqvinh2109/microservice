package com.vn.microservice.users.rest;

import com.vn.microservice.users.dto.UserDto;
import com.vn.microservice.users.model.CreateUserRequestModel;
import com.vn.microservice.users.model.CreateUserResponseModel;
import com.vn.microservice.users.model.UserProductSearch;
import com.vn.microservice.users.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserProductSearch> getUserProductSearch(@PathVariable("userId") String userId) {
        UserDto userDto = userService.getUserById(userId);
        UserProductSearch userProductSearch = new ModelMapper().map(userDto, UserProductSearch.class);

        return ResponseEntity.status(HttpStatus.OK).body(userProductSearch);
    }

}
