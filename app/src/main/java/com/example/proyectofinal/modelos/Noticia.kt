package com.example.proyectofinal.modelos

data class Noticia(
    val id : Int,
    val titulo : String,
    val descripcion : String,
    val cuerpo : String,
    val tipo : String,
    val autor : String,
    val fecha : String,
    val hora : String
)

fun getNoticias(): List<Noticia> = listOf(
    Noticia(id = 1, titulo = "Noticia 1", descripcion = "Descripción de la noticia 1", cuerpo = "Cuerpo de la noticia 1", tipo = "Aviso", autor = "Albergue 1", hora = "5:33 PM", fecha = "Oct 10, 2025"),
    Noticia(id = 2, titulo = "Noticia 2", descripcion = "Descripción de la noticia 2", cuerpo = "Cuerpo de la noticia 2", tipo = "Evento",autor = "Albergue 2", hora = "9:15 AM", fecha = "Oct 11, 2025"),
    Noticia(id = 3, titulo = "Noticia 3", descripcion = "Descripción de la noticia 3", cuerpo = "Cuerpo de la noticia 3", tipo = "Aviso", autor = "Albergue 3", hora = "1:00 PM", fecha = "Oct 12, 2025"),
    Noticia(id = 4, titulo = "Noticia 4", descripcion = "Descripción de la noticia 4", cuerpo = "Cuerpo de la noticia 4", tipo = "Evento", autor = "Albergue 4", hora = "8:10 PM", fecha = "Oct 13, 2025")
)