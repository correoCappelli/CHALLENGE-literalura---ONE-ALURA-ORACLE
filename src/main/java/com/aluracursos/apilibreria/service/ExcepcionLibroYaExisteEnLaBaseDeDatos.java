package com.aluracursos.apilibreria.service;

public class ExcepcionLibroYaExisteEnLaBaseDeDatos extends  RuntimeException {
    public ExcepcionLibroYaExisteEnLaBaseDeDatos(String texto){
        super(texto);
    }
}
