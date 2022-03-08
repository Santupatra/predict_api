package com.santu.predict.mapper;

import org.springframework.stereotype.Component;

import com.santu.predict.model.Registration;
import com.santu.predict.model.User;

@Component
public class UserMapper {

	public User getUserFromRegistration(Registration registration) {

		User user = new User();
		user.setName(registration.getName());
		user.setEmail(registration.getEmail());
		user.setPassword(registration.getPassword());
		user.setPhone(registration.getPhone());
		user.setRoles(registration.getRoles());

		return user;
	}

}
