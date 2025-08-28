package com.malfaa.pmdp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PlataformaMentoriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlataformaMentoriaApplication.class, args);
	}

}
