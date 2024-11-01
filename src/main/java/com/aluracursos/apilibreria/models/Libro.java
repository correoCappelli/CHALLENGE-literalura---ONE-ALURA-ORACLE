package com.aluracursos.apilibreria.models;

import com.fasterxml.jackson.core.io.DataOutputAsStream;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")


public class Libro {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true, name = "titulo")
    private String titulo;

    @Enumerated(EnumType.STRING)
    private Idiomas categoria;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Persona autor;

    private Integer cantidadDeDescargas;

    public Idiomas getCategoria() {
        return categoria;
    }

    public void setCategoria(Idiomas categoria) {
        this.categoria = categoria;
    }

    public Libro(){};


    @Override
    public String toString() {
        String texto ="""
                ************** LIBRO ******************
                Id : %s
                titulo : %s
                categoria : %s 
                cantidad de descargas : %s
                autor : %s
                ***************************************
                """;

        return String.format(texto,titulo,Id,categoria,cantidadDeDescargas,autor.getNombre());
    }

    public Libro(DatosLibros datosLibros) {

        if (Objects.equals(datosLibros.idiomas().get(0), "fr")) {
            this.categoria = Idiomas.valueOf("FRANCES");
        } else if (Objects.equals(datosLibros.idiomas().get(0), "es")) {
            this.categoria = Idiomas.valueOf("ESPAÑOL");
        } else if (Objects.equals(datosLibros.idiomas().get(0), "en")) {
            this.categoria = Idiomas.valueOf("INGLES");
        }

        this.titulo = datosLibros.titulo();

        this.cantidadDeDescargas = datosLibros.cantidadDeDEscargas();

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getCantidadDeDescargas() {
        return cantidadDeDescargas;
    }

    public void setCantidadDeDescargas(Integer cantidadDeDescargas) {
        this.cantidadDeDescargas = cantidadDeDescargas;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    public Persona getAutor() {
        return autor;
    }

    public void setAutor(Persona autor) {
        this.autor = autor;
    }


}
