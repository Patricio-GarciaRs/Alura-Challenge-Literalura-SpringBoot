package com.aluraChallenge.literalura.models;

import com.aluraChallenge.literalura.dto.BookData;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="libros")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private Set<String> idiomas;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "autores_libros",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private Set<Authors> autores;
    private String descargas;

    public Books(BookData datosLibro) {
        this.titulo = datosLibro.titulo();
        this.autores = datosLibro.autores();
        this.idiomas = datosLibro.idiomas();
        this.descargas = datosLibro.descargas();
    }

    public Books() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public Set<String> getIdiomas() {
        return idiomas;
    }

    public void setIdioma(Set<String> idioma) {
        this.idiomas = idioma;
    }



    public Set<Authors> getAutores() {
        return autores;
    }

    public void setAutores(Set<Authors> autores) {
        this.autores = autores;
    }
    public String getDescargas() {
        return descargas;
    }

    public void setDescargas(String descargas) {
        this.descargas = descargas;
    }
}
