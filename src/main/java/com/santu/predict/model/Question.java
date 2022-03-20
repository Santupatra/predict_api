package com.santu.predict.model;

import java.util.List;

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
@Document(collection = "Question")
@JsonInclude(value = Include.NON_NULL)
public class Question {

	@Transient
	public static final String SEQUENCE_NAME = "question_sequence";

	@Id
	private long id;
	private String questionName;
	private boolean active;
    private List<Option> options;
    
}
