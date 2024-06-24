package com.maslab.clinicamaslabback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Clinica Maslab Back-end", version = "1.0", description = "Documentation of API v1.0"))
public class ClinicamaslabbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicamaslabbackApplication.class, args);
	}

}
