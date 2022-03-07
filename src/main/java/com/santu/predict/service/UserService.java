package com.santu.predict.service;

import com.santu.predict.model.User;
import com.santu.predict.model.UserDto;
import com.santu.predict.model.UserOtp;

public interface UserService {
    User save(UserDto userDto);
    User verifyOtp(UserOtp userOtp);
}
