package com.santu.predict.model;

import lombok.Data;

@Data
public class UserDto {

    private String name;
    private String email;
    private String password;
    private String phone;
    private String role;

    public User getUserFromDto(){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setRole(role);
        return user;
    }
    
}