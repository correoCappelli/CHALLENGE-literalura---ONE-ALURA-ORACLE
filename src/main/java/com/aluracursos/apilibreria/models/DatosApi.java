package com.aluracursos.apilibreria.models;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosApi(
    @JsonAlias("results") List<DatosLibros> resultadosApi
)
{
}

