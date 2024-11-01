package com.aluracursos.apilibreria.repository;

import com.aluracursos.apilibreria.models.Libro;
import com.aluracursos.apilibreria.models.Persona;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona,Long> {
    @Query("SELECT a FROM Persona a WHERE SIZE(a.libros) > 0")
    List<Persona> listarAutoresQueTenganLibrosEscritos();
    @Query("SELECT a FROM Persona a WHERE a.fechaDeFallecimiento > :anioIngresado AND :anioIngresado > a.fechaDeNacimiento")
    List<Persona> listarAutoresQueEstabanVivosEnUnAnio(int anioIngresado);
    @Query("SELECT a FROM Persona a WHERE a.nombre ILIKE %:nombre%")
    Persona autorPorNombre(String nombre);
    @Query("SELECT a FROM Persona a WHERE a.nombre ILIKE %:nombre%")
    Optional<Persona> autorPorNombreLista(String nombre);
    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE autores ADD CONSTRAINT nombre_autor_unico UNIQUE (nombre)", nativeQuery = true)
    void applyUniqueConstraint();
    @Query(value = "SELECT COUNT(*) FROM information_schema.table_constraints WHERE table_name = 'autores' AND constraint_type = 'UNIQUE' AND constraint_name = 'nombre_autor_unico'", nativeQuery = true)
    int checkUniqueConstraint();
    Optional<Persona> findByNombre(String nombre);
    @Query("SELECT DISTINCT a FROM Persona a LEFT JOIN FETCH a.libros")
    List<Persona> listarTodosLosAutoresYSusLibros();
}
