package com.santu.predict.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.santu.predict.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findByEmail(String email);
}