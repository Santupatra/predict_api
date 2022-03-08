package com.santu.predict.dao;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.santu.predict.model.User;

@Repository
public interface UserDao extends MongoRepository<User, Long> {
	User findByEmail(String email);

}