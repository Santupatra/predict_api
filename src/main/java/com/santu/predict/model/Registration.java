package com.santu.predict.model;

import java.util.Set;

import lombok.Data;

@Data
public class Registration {

    private String name;
    private String email;
    private String password;
    private String phone;
    private Set<Role> roles;
    
}