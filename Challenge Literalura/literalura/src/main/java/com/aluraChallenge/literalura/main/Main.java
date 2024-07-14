package com.aluraChallenge.literalura.main;

import com.aluraChallenge.literalura.controller.Controller;
import com.aluraChallenge.literalura.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Main {
    Controller controller;
    View view;
    private Scanner teclado = new Scanner(System.in);

    @Autowired
    public Main(Controller controller, View view) {
        this.controller = controller;
        this.view = view;
    }

    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            view.paintMenu();
            opcion = teclado.nextInt();
            view.executeOption(opcion);
        }
        System.exit(0);
    }
}