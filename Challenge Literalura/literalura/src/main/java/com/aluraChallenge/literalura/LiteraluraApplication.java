package com.aluraChallenge.literalura;

import com.aluraChallenge.literalura.controller.Controller;
import com.aluraChallenge.literalura.main.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.aluraChallenge.literalura.view.View;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	Controller controller;
	@Autowired
	View vista;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	public void run(String... args) throws Exception {
		Main main = new Main(controller, vista);
		main.mostrarMenu();
	}
}
