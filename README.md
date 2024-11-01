

# ONE Alura + Oracle

## api-literalura
## Instructores
### Ing Bruno Ellerbach
### Ing Genesys Rondon
### Prof Eric Monné Fraga de Oliveira

# API Gutendex
### https://gutendex.com/?ref=public_apis



# SUMARIO


- [ONE Alura + Oracle](#one-alura--oracle)
    - [api-literalura](#api-conversordemonedas)
    - [Instructores](#instructores)
        - [Ing Bruno Ellerbach](#ing-bruno-ellerbach)
        - [Ing Genesys Rondon](#ing-genesys-rondon)
        - [Prof Eric Monné Fraga de Oliveira](#Prof Eric Monné Fraga de Oliveira)
- [API Gutendex](#API Gutendex)
    - [https://gutendex.com/?ref=public_apis](#API Gutendex)
- [SUMARIO](#sumario)
    - [Introduccion](#introduccion)
    - [EndPoints](#endpoints)
    - [URL con todos los codigos de monedas posibles](#url-con-todos-los-codigos-de-monedas-posibles)
    - [archivo de historial successFECHA.txt](#archivo-de-historial-successfechatxt)
    - [Variables de Entorno (.Env)](#variables-de-entorno-env)
    - [EXCEPCIONES](#excepciones)


## Introduccion

Literalura es una aplicacion JAVA + Spring + JPA & JPQL de manejo de base de datos relacional SQL de libros y autores.
Se utiliza en conjunto al programa PgAdmin4 (postgreSQL)
Es un Challenge del programa ONE de ALura y Oracle.
La API para la obtencion de datos es Gutendex

## Dependencias

![dependencias](/imagenes/img.png)


## EndPoints API Gutendex

|PETICION | URL                                                                                                   | DESCRIPCION                                                                                                                                |
--- |-------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
|GET| https://gutendex.com/books                                                                            | no devuelve 32 libros mas populares. Formato DatosAPI                                                                                      |
|GET| https://gutendex.com/books?search=texto+a+buscar                                       | nos devuelve un libro buscado por parte del autor o titulo                                                                                 |


Ejemplo de la respuesta de la busqueda de un libro  :

```json
{
  "id": <number of Project Gutenberg ID>,
  "title": <string>,
  "subjects": <array of strings>,
  "authors": <array of Persons>,
  "translators": <array of Persons>,
  "bookshelves": <array of strings>,
  "languages": <array of strings>,
  "copyright": <boolean or null>,
  "media_type": <string>,
  "formats": <Format>,
  "download_count": <number>
}
}
```
En nuestro caso utilizaremos un solo autor por libro (OneToMany : un autor tiene escritos muchos libros ManyToOne)

El autor es del tipo Persona

``` json
{
  "birth_year": <number or null>,
  "death_year": <number or null>,
  "name": <string>
}
```    

## modelos de Clases Record para obtener los datos
### DatosAPI 

``` java
public record DatosApi(
    @JsonAlias("results") List<DatosLibros> resultadosApi
)
{
}
```
### DatosLibros 
``` java
public record DatosLibros(
        @JsonAlias("title") String titulo,

        @JsonAlias("authors") List<DatosPersonas> autores,

        @JsonAlias("languages")List<String> idiomas,

        @JsonAlias("download_count") Integer cantidadDeDEscargas

) {
```
### DatosPersonas
``` java
public record DatosPersonas(

        @JsonAlias("name") String nombre,


        @JsonAlias("birth_year")String fechaDeNacimiento,


        @JsonAlias("death_year")String fechaDeFallecimiento


) { }
```
## Metodo init() @PostConstruct que inicia la constriccion de titulo de pelicula unico en la base de datos si no fue ya iniciada
```java
@PostConstruct
public void init() {
    int contador = repositorioPersona.checkUniqueConstraint();
    if (contador == 0) {
        repositorioPersona.applyUniqueConstraint();
    }
}
```

## Variables de Entorno application.properties

```java
spring.application.name=apilibreria
spring.datasource.url=jdbc:postgresql://${DB_HOST}/db_literalura
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
hibernate.dialect=org.hibernate.dialect.HSQLDialect

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.format-sql=false 
```

## EXCEPCIONES CUSTOMIZADAS

## ExcepcionLibroYaExisteEnLaBaseDeDatos
```java
            package com.aluracursos.apilibreria.service;

public class ExcepcionLibroYaExisteEnLaBaseDeDatos extends  RuntimeException {
    public ExcepcionLibroYaExisteEnLaBaseDeDatos(String texto){
        super(texto);
    }
}
```            