package com.direct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;

@SpringBootApplication (exclude = RepositoryRestMvcAutoConfiguration.class)
public class DirectServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(DirectServerApplication.class, args);
	}
}
