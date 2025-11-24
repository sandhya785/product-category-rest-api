package com.learnspringboot.product;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Slf4j
@OpenAPIDefinition(
        info = @Info(
                title = "Product Service REST API Documentation",
                description = "Product Service REST API",
                version = "v1",
                contact = @Contact(
                        name = "sandhya",
                        email = "sandhya@gmail.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "sharepoint URL Product Service API",
                url="example.com"
        )
)
@SpringBootApplication
@EnableSpringDataWebSupport
public class ProductApplication {

	public static void main(String[] args) {

        SpringApplication.run(ProductApplication.class, args);

	}

}
