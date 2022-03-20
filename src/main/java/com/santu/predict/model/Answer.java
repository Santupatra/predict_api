package com.santu.predict.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Answer")
@JsonInclude(value = Include.NON_NULL)
public class Answer {

	@Transient
	public static final String SEQUENCE_NAME = "answer_sequence";

	@Id
	private long id;
	private long questionId;
	private long optionId;
	private long userId;
}
