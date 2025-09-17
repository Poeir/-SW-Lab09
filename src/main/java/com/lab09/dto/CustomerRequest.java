package com.lab09.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustomerRequest {
	@NotBlank(message = "Customer name must not be blank!")
	@JsonProperty("name")
	private String name;
	
	@NotBlank(message = "Email name must not be blank!")
	@Email(message = "Email must be valid")
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
