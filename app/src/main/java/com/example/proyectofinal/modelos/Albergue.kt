package com.example.proyectofinal.modelos

data class Albergue(
    val id : Int = 0,
    val nombre : String = "Caritas de Monterrey",
    val celular : Long = 6442810392,
    val capacidad : Int = 60,
    val cuota : Int = 30,
    val disponibilidad : Int = 25,
    val direccion : String = "Av. Eugenio Garza Sada, Tecnologico, Monterrey",
    val latitud : Double = 0.0,
    val longitud : Double = 0.0,
    val servicios : List<Servicios> = listOf(Servicios(id = 1, nombre = "Duchas", precio = "10"),
        Servicios(id = 2, nombre = "Desayuno", precio = "15"), Servicios(id = 3, nombre = "Comida", precio = "15"),
        Servicios(id = 4, nombre = "Cena", precio = "10"),Servicios(id = 5, nombre = "Lavanderia", precio = "10"),
        Servicios(id = 6, nombre = "Consulta Medica", precio = "Gratis"), Servicios(id = 7, nombre = "Traslado", precio = "20"))
)

fun getAlbergues(): List<Albergue> = listOf<Albergue>(
        Albergue(id = 1,nombre = "Posada del Peregrino", celular = 528113402208, disponibilidad = 30, latitud = 25.6833433710119,longitud = -100.34547089691362, direccion = "Franciso G. Sada, Av. Simón Bolívar 190, Deportivo Obispado, Chepevera, 64030 Monterrey, N.L.\n"),
        Albergue(id = 2,nombre = "Divina Providencia", celular = 6471270465, disponibilidad = 25,latitud = 25.668389672297707,longitud = -100.30311694417804, direccion = "Florencio Antillón 1223, Centro, 64000 Monterrey, N.L"),
        Albergue(id = 3,nombre = "Apodaca", celular = 6471270465, disponibilidad = 45, latitud = 25.79156194467012,longitud = -100.13871492023976, direccion = "Av. Miguel Alemán S/N, 66627 N.L.")
    )