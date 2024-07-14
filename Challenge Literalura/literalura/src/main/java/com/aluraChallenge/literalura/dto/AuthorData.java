package com.aluraChallenge.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorData(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer añoDeNacimiento,
        @JsonAlias("death_year") Integer añoDeFallecimiento) {
}