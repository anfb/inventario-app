package com.inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class InventarioApp {

	public static void main(String[] args) {
		SpringApplication.run(InventarioApp.class, args);
	}

}
