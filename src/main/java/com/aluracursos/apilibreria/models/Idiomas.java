package com.aluracursos.apilibreria.models;

import jdk.jfr.Category;

public enum Idiomas {


    ESPAÑOL(new String[]{"Spanish", "ESP", "Es", "es"}),
    FRANCES(new String[]{"French", "FRA", "Fr", "fr"}),
    INGLES(new String[]{"English", "ENG", "En", "en"});

    public String[] idiomaApi;

    public String[] getIdiomaApi() {
        return idiomaApi;
    }

    Idiomas(String[] idiomaApi) {
        this.idiomaApi = idiomaApi;
    }

    public static Idiomas fromEspañol(String texto) {
        for (Idiomas idioma : Idiomas.values()) {
            for (String idiomas : idioma.getIdiomaApi()) {
                if (idiomas.equalsIgnoreCase(texto)) {
                    return idioma;
                }
            }
        }
            throw new IllegalArgumentException("Ningun idioma existe " + texto);
        }
    }


