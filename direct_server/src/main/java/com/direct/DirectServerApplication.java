package com.direct;

import com.direct.models.XlsCreator;
import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class) // Don't add /api route
@PropertySource({"classpath:application.properties"})
public class DirectServerApplication {
	public static void main(String[] args) throws UnsupportedEncodingException {
	    setJvmProperties();
            SpringApplication.run(DirectServerApplication.class, args);
	}

	private static void setJvmProperties() {
		System.setProperty("DIRECT_ADMIN_NAME", "developer");
		System.setProperty("DIRECT_ADMIN_PASSWORD", "pass");
	}

	@Bean
	@Scope("prototype")
	public XlsCreator xlsCreator() {
		return new XlsCreator();
	}
}
