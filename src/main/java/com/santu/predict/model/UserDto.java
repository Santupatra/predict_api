package com.santu.predict.model;

import java.util.Set;

import lombok.Data;

@Data
public class UserDto {

    private String name;
    private String email;
    private String password;
    private String phone;
    private Set<Role> roles;

    public User getUserFromDto(){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setRoles(roles);
        return user;
    }
    
}