package com.example.proyectofinal.modelos

data class Reserva(
    val id : Int = 0,
    val nombreAlbergue : String = "",
    val nombreResponsable : String = "",
    val apellidoResponsable : String = "",
    val celular : Long = 1000000,
    val fechaLlegada : String = "",
    val fechaSalida : String = "",
    val numPersonas : Int = 1,
)

fun getReservas(): List<Reserva> = listOf(
    Reserva(id = 1, nombreAlbergue = "Albergue ", nombreResponsable = "Juan", apellidoResponsable = "Perez", celular = 1000000, fechaLlegada = "2023-01-01", fechaSalida = "2023-01-02", numPersonas = 1),
    Reserva(id = 2, nombreAlbergue = "Albergue 2", nombreResponsable = "Juan", apellidoResponsable = "Perez", celular = 1000000, fechaLlegada = "2023-01-01", fechaSalida = "2023-01-02", numPersonas = 2),
    Reserva(id = 3, nombreAlbergue = "Albergue 3", nombreResponsable = "Juan", apellidoResponsable = "Perez", celular = 1, fechaLlegada = "2023-01-01", fechaSalida = "2023-01-02", numPersonas = 3)
)