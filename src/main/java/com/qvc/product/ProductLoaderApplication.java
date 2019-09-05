package com.qvc.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"${datasource.users.properties.location}"})
public class ProductLoaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductLoaderApplication.class, args);
	}
}
