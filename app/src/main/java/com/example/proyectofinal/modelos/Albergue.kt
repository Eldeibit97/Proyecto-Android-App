package com.example.proyectofinal.modelos

data class Albergue(
    val nombre : String = "Caritas de Monterrey",
    val celular : Long = 6442810392,
    val capacidad : Int = 25,
    val direccion : String = "Av. Eugenio Garza Sada, Tecnologico, Monterrey"
)

fun getAlbergues(): List<Albergue> = listOf<Albergue>(
        Albergue(nombre = "Caritas Centro", celular = 6471270465, capacidad = 0, direccion = "Av. Constitución 1234, Centro, Monterrey, NL."),
        Albergue(nombre = "Caritas Centro", celular = 6471270465, capacidad = 0, direccion = "Av. Constitución 1234, Centro, Monterrey, NL."),
        Albergue(nombre = "Caritas Centro", celular = 6471270465, capacidad = 0, direccion = "Av. Constitución 1234, Centro, Monterrey, NL.")
    )
