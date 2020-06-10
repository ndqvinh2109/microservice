package com.vn.microservice.users.service;

import com.vn.microservice.users.dto.UserDto;
import com.vn.microservice.users.entity.UserEntity;
import com.vn.microservice.users.entity.UsersRepository;
import com.vn.microservice.users.model.ProductResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @Override
    public UserDto createUser(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        usersRepository.save(userEntity);

        UserDto returnUserDto = modelMapper.map(userEntity, UserDto.class);

        return returnUserDto;
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = usersRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return new ModelMapper().map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUserById(String id) {
        UserEntity userEntity = usersRepository.findByUserId(id);
        if (userEntity == null) throw new UsernameNotFoundException("User not found");

        String shoppingUrl = "http://shopping-searching/api/products?productSearchKey=ANKER&searchBy=NAME&sortBy=NAME ASC";

        ResponseEntity<List<ProductResponse>> responseEntity = restTemplate.exchange(shoppingUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductResponse>>() {
        });

        List<ProductResponse> productResponses = responseEntity.getBody();

        UserDto returnUserDto = new ModelMapper().map(userEntity, UserDto.class);
        returnUserDto.setProductResponseList(productResponses);

        return returnUserDto;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = usersRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),true, true, true, true, new ArrayList<>());
    }
}
