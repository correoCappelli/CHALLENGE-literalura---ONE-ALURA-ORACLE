package com.aluracursos.apilibreria.repository;

import com.aluracursos.apilibreria.models.Idiomas;
import com.aluracursos.apilibreria.models.Libro;
import com.aluracursos.apilibreria.models.Persona;
import org.aspectj.weaver.patterns.PerObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LibroRepository extends JpaRepository<Libro,Long> {
List<Libro> findAllByOrderByCantidadDeDescargasDesc();
List<Libro> findByCategoria(Idiomas idioma);
Optional<Libro> findByTitulo(String titulo);
@Query("SELECT l FROM Libro l JOIN FETCH l.autor")
List<Libro> BuscarLibrosConSuAutor();
@Query("SELECT l FROM Libro l JOIN FETCH l.autor WHERE l.categoria = :categoria")
List<Libro> findBooksByLanguageWithAuthors(@Param("categoria") Idiomas categoria );

}
