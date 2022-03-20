package com.santu.predict.util;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.santu.predict.model.Question;

@Component
public class VerifyUtil {

	public void verifyQuestion(Question question) {
		if(ObjectUtils.isEmpty(question.getOptions())) {
			throw new IllegalArgumentException("Mustbe one or more option present");
		}
	}

}
