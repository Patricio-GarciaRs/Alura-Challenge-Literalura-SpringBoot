package com.aluraChallenge.literalura.controller;

import com.aluraChallenge.literalura.models.Authors;
import com.aluraChallenge.literalura.models.Books;
import com.aluraChallenge.literalura.repository.AuthorRepository;
import com.aluraChallenge.literalura.repository.BooksRepository;
import com.aluraChallenge.literalura.service.AuthorService;
import com.aluraChallenge.literalura.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {
    private AuthorRepository autorRepository;
    private BooksRepository libroRepository;
    private BooksService libroService;
    private AuthorService autorService;
    @Autowired
    public Controller(AuthorRepository autorRepository, BooksRepository libroRepository, BooksService service, AuthorService autorService) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
        this.libroService = service;
        this.autorService = autorService;
    }
    public Books getDataBooks(String tituloDeLibro) {
        Optional<Books> libro = libroService.recoverBook(tituloDeLibro);
        return libro.get();
    }
    public List<Books> listBooksRegistred() {
        return libroRepository.findAll();
    }
    public List<Authors> listAuthorsRegistred() {
        return autorRepository.findAll();
    }
    public List<Authors>  listAuthorsAliveInYear(int año) {
        return autorService.getAuthorsAliveInYear(año);
    }
    public List<Books> listBooksByLanguage(String idioma) {
        return libroService.getBooksByLanguage(idioma);
    }

}