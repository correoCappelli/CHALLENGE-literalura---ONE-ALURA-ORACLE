package com.aluracursos.apilibreria.models;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)


public record DatosPersonas(

        @JsonAlias("name") String nombre,


        @JsonAlias("birth_year")String fechaDeNacimiento,


        @JsonAlias("death_year")String fechaDeFallecimiento


) { }
