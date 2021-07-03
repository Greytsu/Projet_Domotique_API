package com.example.ProjetDomotiqueAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.example.ProjetDomotiqueAPI.*" })
//@ComponentScan({ "com.example.ProjetDomotiqueAPI" })
public class ProjetDomotiqueApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetDomotiqueApiApplication.class, args);
	}

}
