package com.santu.predict.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santu.predict.dao.AnswerDao;
import com.santu.predict.dao.QuestionDao;
import com.santu.predict.model.Answer;
import com.santu.predict.model.Option;
import com.santu.predict.model.Question;
import com.santu.predict.service.QuestionService;

import lombok.extern.log4j.Log4j2;

@Service(value = "questionService")
@Log4j2
public class QuestionServiceImpl implements  QuestionService {

	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@Autowired
	private QuestionDao questionDao;
	
	@Autowired
	private AnswerDao answerDao;
	

	@Override
	public Question add(Question question) {
		log.info("QuestionServiceImpl:add start");
		question.setId(sequenceGeneratorService.generateSequence(Question.SEQUENCE_NAME));
		question.setActive(true);
		question.getOptions().forEach(option -> {
			option.setId(sequenceGeneratorService.generateSequence(Option.SEQUENCE_NAME));
		});
		return questionDao.save(question);
	}

	@Override
	public List<Question> getAll() {
		
		return questionDao.findByActiveTrue();
	}

	@Override
	public Answer chooseOption(Answer answer) {
		answer.setId(sequenceGeneratorService.generateSequence(Answer.SEQUENCE_NAME));
		return answerDao.save(answer);
		
	}


}
