package com.direct;

import com.direct.models.XlsCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class) // Don't add /api route
public class DirectServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(DirectServerApplication.class, args);
	}

	@Bean
	@Scope("prototype")
	public XlsCreator xlsCreator() {
		return new XlsCreator();
	}
}
