package org.faillon.documentsapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Authors And Documents API",
		description = "API to expose CRUD operations on Authors and Documents",
		version = "1.0.0",
		contact = @Contact(
				name = "Felipe Aillon",
				email = "faillon@gmail.com"
		)
))
public class DocumentsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentsApiApplication.class, args);
	}

}
