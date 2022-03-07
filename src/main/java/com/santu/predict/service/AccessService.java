package com.santu.predict.service;

import com.santu.predict.model.User;
import com.santu.predict.model.Registration;
import com.santu.predict.model.UserOtp;

public interface AccessService {
    User save(Registration userDto);
    User verifyOtp(UserOtp userOtp);
}
