package com.example.proyectofinal.modelos

data class Albergue(
    val id : Int = 0,
    val nombre : String = "Caritas de Monterrey",
    val celular : Long = 6442810392,
    val capacidad : Int = 60,
    val cuota : Int = 30,
    val disponibilidad : Int = 25,
    val direccion : String = "Av. Eugenio Garza Sada, Tecnologico, Monterrey",
    val servicios : List<Servicios> = listOf(Servicios(id = 1, nombre = "Duchas", precio = "10"),
        Servicios(id = 2, nombre = "Desayuno", precio = "15"), Servicios(id = 3, nombre = "Comida", precio = "15"),
        Servicios(id = 4, nombre = "Cena", precio = "10"),Servicios(id = 5, nombre = "Lavanderia", precio = "10"),
        Servicios(id = 6, nombre = "Consulta Medica", precio = "Gratis"), Servicios(id = 7, nombre = "Traslado", precio = "20"))
)

fun getAlbergues(): List<Albergue> = listOf<Albergue>(
        Albergue(id = 1,nombre = "Caritas Centro", celular = 6471270465, disponibilidad = 30, direccion = "Av. Constitución 1234, Centro, Monterrey, NL."),
        Albergue(id = 2,nombre = "Caritas Centro", celular = 6471270465, disponibilidad = 25, direccion = "Av. Constitución 1234, Centro, Monterrey, NL."),
        Albergue(id = 3,nombre = "Caritas Centro", celular = 6471270465, disponibilidad = 45, direccion = "Av. Constitución 1234, Centro, Monterrey, NL.")
    )