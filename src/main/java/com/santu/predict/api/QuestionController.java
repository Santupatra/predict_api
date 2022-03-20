package com.santu.predict.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.santu.predict.model.Answer;
import com.santu.predict.model.Question;
import com.santu.predict.service.QuestionService;
import com.santu.predict.util.VerifyUtil;

import lombok.extern.log4j.Log4j2;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/question")
@Log4j2
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private VerifyUtil verifyUtil;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/add")
	public Question addQuestion(@RequestBody Question question) {
		log.info("QuestionController:addQuestion start");
		verifyUtil.verifyQuestion(question);
		return questionService.add(question);
	}
	
	@GetMapping(value = "/list")
	public List<Question> getQuestions() {
		log.info("QuestionController:getQuestions start");
		return questionService.getAll();
	}
	
	@PostMapping(value = "/choose")
	public Answer chooseOption(@RequestBody Answer answer) {
		log.info("QuestionController:chooseOption start");
		return questionService.chooseOption(answer);
	}

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/userping", method = RequestMethod.GET)
	public String userPing() {
		return "Any User Can Read This";
	}

}
