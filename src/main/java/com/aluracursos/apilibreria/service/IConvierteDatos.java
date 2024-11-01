package com.aluracursos.apilibreria.service;


    public interface IConvierteDatos {
        <T> T obtenerDatos(String json, Class<T> clase);
    }

