package com.aluraChallenge.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.aluraChallenge.literalura.models.Authors;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") Set<Authors> autores,
        @JsonAlias("languages")Set<String> idiomas,
        @JsonAlias("download_count")String descargas

){
}