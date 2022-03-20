package com.santu.predict.dao;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.santu.predict.model.Question;

@Repository
public interface QuestionDao extends MongoRepository<Question, Long> {

	List<Question> findByActiveTrue();

}