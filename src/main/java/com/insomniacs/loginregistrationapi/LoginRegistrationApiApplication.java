package com.insomniacs.loginregistrationapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition (
	info = @Info(
		title = "Insomniacs Practical Round - LoginRegistrationService"
	)
)
public class LoginRegistrationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginRegistrationApiApplication.class, args);
	}

}
