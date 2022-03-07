package com.santu.predict.model;

import lombok.Data;

@Data
public class Account {

	private String email;
	private String upi;
	private String bonus;
	private String saving;
	private String wallet; // saving+bonus
}
