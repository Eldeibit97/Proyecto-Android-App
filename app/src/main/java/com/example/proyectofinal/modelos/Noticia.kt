package com.example.proyectofinal.modelos

data class Noticia(
    val id : Int,
    val titulo : String,
    val descripcion : String,
    val cuerpo : String,
    val tipo : String,
    val autor : String,
    val fecha : String,
)

fun getNoticias(): List<Noticia> = listOf(
    Noticia(id = 1, titulo = "Noticia 1", descripcion = "Descripción de la noticia 1", cuerpo = "Cuerpo de la noticia 1", tipo = "Aviso", autor = "Autor 1", fecha = "2023-10-10"),
    Noticia(id = 2, titulo = "Noticia 2", descripcion = "Descripción de la noticia 2", cuerpo = "Cuerpo de la noticia 2", tipo = "Evento",autor = "Autor 2", fecha = "2023-10-11"),
    Noticia(id = 3, titulo = "Noticia 3", descripcion = "Descripción de la noticia 3", cuerpo = "Cuerpo de la noticia 3", tipo = "Aviso", autor = "Autor 3", fecha = "2023-10-01"),
    Noticia(id = 4, titulo = "Noticia 4", descripcion = "Descripción de la noticia 4", cuerpo = "Cuerpo de la noticia 4", tipo = "Evento", autor = "Autor 4", fecha = "2023-10-02")
)