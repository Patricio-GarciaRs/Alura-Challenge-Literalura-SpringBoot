package com.aluraChallenge.literalura.view;

import com.aluraChallenge.literalura.controller.Controller;
import com.aluraChallenge.literalura.models.Authors;
import com.aluraChallenge.literalura.models.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class View {
    @Autowired
    Controller controller;
    private Scanner teclado = new Scanner(System.in);
    public void searchBookByTitle() {
        System.out.println(MessagesBooks.MessageBookRequest[1]);
        var tituloDeLibro = teclado.nextLine();
        Books libro = controller.getDataBooks(tituloDeLibro);
        if (libro != null) {
            MessagesBooks.printBookDetails(libro);
        }
    }
    public void viewListBooksRegistred() {
        List<Books> libros = controller.listBooksRegistred();
        System.out.println(MessagesBooks.outMessageBook[4]);
        MessagesBooks.printBooksRegistred(libros);
    }
    public void printAuthorsRegistred(List<Authors> autores) {
        System.out.println(MessagesAuthor.outMessageAutor[0]);
        for (Authors autor : autores) {
            MessagesAuthor.printAutor(autor);
            List<String> writtenBooks = new ArrayList<>();
            for (Books libro : autor.getLibros()) {
                writtenBooks.add(libro.getTitulo());
            }
            System.out.println(MessagesAuthor.outMessageAutor[1] + writtenBooks);
            System.out.println(MessagesAuthor.outMessageAutor[4]);
        }
    }
    public void viewListAuthorsRegistred() {
        List<Authors> autores = controller.listAuthorsRegistred();
        this.printAuthorsRegistred(autores);
    }
    public void ShowMenuLanguage(){
        System.out.println(MessagesBooks.MessageBookRequest[0]);
        System.out.println( MessagesBooks.menuLanguages );
    }
    public void viewListByLanguage() {
        this.ShowMenuLanguage();
        Scanner enter = new Scanner(System.in);
        var idioma = enter.nextLine();
        List<Books> libros = controller.listBooksByLanguage(idioma.toLowerCase());
        if (libros.isEmpty()) {
            System.out.println(MessagesBooks.outMessageBook[5] + idioma + "\n");
        }else {
            System.out.println(MessagesBooks.outMessageBook[7]+idioma + "\n");
            MessagesBooks.printBooksRegistred(libros);
        }
    }
    public void paintMenu(){
        System.out.println(MessagesBooks.menu);
    }
    public void viewSearchAuthorAliveInYear() {
        System.out.println(MessagesAuthor.messageAutorRequest[0]);
        var year = teclado.nextInt();
        List<Authors> autoresVivos = controller.listAuthorsAliveInYear(year);
        if (autoresVivos.isEmpty()) {
            System.out.println(MessagesAuthor.outMessageAutor[3] + year + "\n");
        } else {
            System.out.println(MessagesAuthor.outMessageAutor[5]+ MessagesAuthor.outMessageAutor[2] + year + MessagesAuthor.outMessageAutor[5]);
            this.printAuthorsRegistred(autoresVivos);
        }
    }
    public void executeOption(int opcion){
        switch (opcion) {
            case 1:
                this.searchBookByTitle();
                break;
            case 2:
                this.viewListBooksRegistred();
                break;
            case  3:
                this.viewListAuthorsRegistred();
                break;
            case 4 :
                this.viewSearchAuthorAliveInYear();
                break;
            case 5:
                this.viewListByLanguage();
                break;
            case 0:
                System.out.println("Cerrando la aplicación...");
                break;
            default:
                System.out.println("Opción inválida");
        }
    }
}
