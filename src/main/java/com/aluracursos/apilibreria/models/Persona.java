package com.aluracursos.apilibreria.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")

public class Persona {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "nombre", unique = true)
    private String nombre;

    private String fechaDeNacimiento;

    private String fechaDeFallecimiento;

    //fetch type EAGER para que la funcion de guardado a la DB funcione y no cierre la sesion
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "autor",fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Libro> libros=new ArrayList<>();

    public Persona(){};


@Override
public String toString() {
    String texto ="""
                ************** AUTOR ******************
                Id : %s
                nombre : %s
                fecha de nacimiento : %s 
                fecha de fallecimiento : %s
                libros : %s
                ***************************************
                """;
    return String.format(texto,Id,nombre,fechaDeNacimiento,fechaDeFallecimiento,
            libros.stream()
                    .map(l->l.getTitulo())
                    .toList()
            );
}

    public Persona(DatosPersonas personas) {
        this.nombre = personas.nombre();
        this.fechaDeNacimiento = personas.fechaDeNacimiento();
        this.fechaDeFallecimiento = personas.fechaDeFallecimiento();

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(String fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
    public void addLibro(Libro libro) {
        this.libros.add(libro);
    }
}
