package com.santu.predict.service;

import java.util.List;

import com.santu.predict.model.User;
import com.santu.predict.model.UserDto;

public interface UserService {
    User save(UserDto user);
    List<User> findAll();
    User findOne(String username);
}
