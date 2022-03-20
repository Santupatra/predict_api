package com.santu.predict.service;

import java.util.List;

import com.santu.predict.model.Answer;
import com.santu.predict.model.Question;

public interface QuestionService {
	
	Question add(Question question);

	List<Question> getAll();

	Answer chooseOption(Answer answer);
}
