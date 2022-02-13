package br.com.wit.calculadora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CalculadoraTaskApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CalculadoraTaskApplication.class, args);
	}

}
