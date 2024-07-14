package com.aluraChallenge.literalura.service;

import com.aluraChallenge.literalura.dto.AuthorData;
import com.aluraChallenge.literalura.models.Authors;
import org.springframework.stereotype.Service;
import com.aluraChallenge.literalura.repository.AuthorRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private AuthorRepository autorRepository;

    public AuthorService(AuthorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Authors> getAuthorsAliveInYear(int año) {
        //Obtener todos los autores de la base de datos
        List<Authors> autores = autorRepository.findAll();
        //filtrar la lista de autores para incluir solo los autores vivos en el año ingresado
        return autores.stream()
                .filter(autor -> Integer.parseInt(autor.getAñoDeNacimiento()) <= año && (autor.getAñoDeFallecimiento() == null || Integer.parseInt(autor.getAñoDeFallecimiento()) >= año))
                .collect(Collectors.toList());
    }
    public Set<Authors> processAuthors(List<Map<String, Object>> authorsData) {
        Set<Authors> autores = new HashSet<>();
        for (Map<String, Object> author : authorsData) {
            autores.add(processAuthorData(author));
        }
        return autores;
    }
    private Authors processAuthorData(Map<String, Object> authorData) {
        String nombre = (String) authorData.get("name");
        Integer añoDeNacimiento = ((Double) authorData.get("birth_year")).intValue();
        Integer añoDeFallecimiento = ((Double) authorData.get("death_year")).intValue();

        AuthorData datosAutor = new AuthorData(nombre, añoDeNacimiento, añoDeFallecimiento);
        Authors autor = new Authors(datosAutor);
        // Si el autor no existe, guardarlo en la base de datos
        Optional<Authors> existingAutor = autorRepository.findByNombreContainsIgnoreCase(nombre);
        if (existingAutor.isEmpty()){
            autorRepository.save(autor);
        } else {
            autor = existingAutor.get();
        }
        return autor;
    }
}