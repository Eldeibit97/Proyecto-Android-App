package com.example.proyectofinal.modelos

data class Albergue(
    val id : Int = 0,
    val nombre : String = "Caritas de Monterrey",
    val celular : Long = 6442810392,
    val capacidad : Int = 60,
    val disponibilidad : Int = 25,
    val direccion : String = "Av. Eugenio Garza Sada, Tecnologico, Monterrey"
)

fun getAlbergues(): List<Albergue> = listOf<Albergue>(
        Albergue(id = 1,nombre = "Caritas Centro", celular = 6471270465, disponibilidad = 30, direccion = "Av. Constitución 1234, Centro, Monterrey, NL."),
        Albergue(id = 2,nombre = "Caritas Centro", celular = 6471270465, disponibilidad = 25, direccion = "Av. Constitución 1234, Centro, Monterrey, NL."),
        Albergue(id = 3,nombre = "Caritas Centro", celular = 6471270465, disponibilidad = 45, direccion = "Av. Constitución 1234, Centro, Monterrey, NL.")
    )