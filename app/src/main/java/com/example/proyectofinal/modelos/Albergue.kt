package com.example.proyectofinal.modelos

data class Albergue(
    val nombre : String = "",
    val celular : Long = 100000000,
    val capacidad : Int = 1,
    val direccion : String = ""
)

fun getAlbergues(): List<Albergue> = listOf<Albergue>(
        Albergue(nombre = "Caritas Centro", celular = 6471270465, capacidad = 0, direccion = "Av. Constitución 1234, Centro, Monterrey, NL."),
        Albergue(nombre = "Caritas Centro", celular = 6471270465, capacidad = 0, direccion = "Av. Constitución 1234, Centro, Monterrey, NL."),
        Albergue(nombre = "Caritas Centro", celular = 6471270465, capacidad = 0, direccion = "Av. Constitución 1234, Centro, Monterrey, NL.")
    )
