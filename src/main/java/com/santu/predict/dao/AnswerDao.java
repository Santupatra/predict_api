package com.santu.predict.dao;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.santu.predict.model.Answer;

@Repository
public interface AnswerDao extends MongoRepository<Answer, Long> {


}