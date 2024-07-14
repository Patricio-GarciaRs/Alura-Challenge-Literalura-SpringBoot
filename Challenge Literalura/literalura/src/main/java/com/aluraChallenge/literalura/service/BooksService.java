package com.aluraChallenge.literalura.service;

import com.aluraChallenge.literalura.view.MessagesBooks;
import com.aluraChallenge.literalura.models.Authors;
import com.aluraChallenge.literalura.models.Books;
import org.springframework.stereotype.Service;
import com.aluraChallenge.literalura.repository.BooksRepository;
import com.aluraChallenge.literalura.dto.BookData;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BooksService {
    private BooksRepository libroRepository;
    private AuthorService autorService;
    private final String URL_BASE = "https://gutendex.com/books?search=";

    public BooksService(BooksRepository libroRepository, AuthorService autorService) {

        this.libroRepository = libroRepository;
        this.autorService = autorService;
    }

    private Optional<Books> processBookData(String tituloDeLibro) {
        //Deserializar el JSON
        Map<String, Object> bookData = ApiServices.getJsonData(URL_BASE, tituloDeLibro);
        // Asignar los datos del JSON a la entidad DatosLibro
        List<Map<String, Object>> results = (List<Map<String, Object>>) bookData.get("results");
        if (!results.isEmpty()) {
            Map<String, Object> result = results.get(0);
            Books libro = processSingleBookData(result);
            return Optional.ofNullable(libro);
        } else {
            System.out.println(MessagesBooks.outMessageBook[0]);
            return Optional.empty();
        }
    }

    public Optional<Books> recoverBook(String titulo){
        Optional<Books> existingLibro = libroRepository.findByTituloContainsIgnoreCase(titulo.toLowerCase());
        if(existingLibro.isEmpty()){
            return processBookData(titulo);
        }
        System.out.println(MessagesBooks.outMessageBook[1]);
        return existingLibro;
    }

    private Books processSingleBookData(Map<String, Object> bookData) {
        String titulo = (String) bookData.get("title");
        Set<Authors> autores = autorService.processAuthors((List<Map<String, Object>>) bookData.get("authors"));
        Set<String> idiomas = new HashSet<>((List<String>) bookData.get("languages"));
        String descargas = bookData.get("download_count").toString();

        BookData datosLibro = new BookData(titulo, autores, idiomas, descargas);
        Books libro = new Books(datosLibro);
        // Guardar el libro en la base de datos
        saveBookInDatabase(libro);
        return libro;
    }
    private Optional<Books> saveBookInDatabase(Books libro) {
        libroRepository.save(libro);
        System.out.println(MessagesBooks.outMessageBook[2]);
        return Optional.ofNullable(libro);
    }
    public List<Books> getBooksByLanguage(String idioma) {
        //Obtener todos los libros de la base de datos
        List<Books> libros = libroRepository.findAll();
        //filtrar la lista de libros para incluir solo los libros con el idioma ingresado
        return libros.stream()
                .filter(libro -> libro.getIdiomas().contains(idioma))
                .collect(Collectors.toList());
    }
}