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

data class TransporteReservation(
    val id: Int,
    val origen: String,
    val destino: String,
    val fecha: String,
    val pasajeros: Int,
)
fun getReservas(): List<Reserva> = listOf(
    Reserva(id = 1, nombreAlbergue = "Albergue ", nombreResponsable = "Juan", apellidoResponsable = "Perez", celular = 8113844318, fechaLlegada = "23-10-2025", fechaSalida = "26-10-2025", numPersonas = 8),
    Reserva(id = 2, nombreAlbergue = "Albergue 2", nombreResponsable = "Juan", apellidoResponsable = "Perez", celular = 8113844318, fechaLlegada = "23-10-2025", fechaSalida = "26-10-2025", numPersonas = 8),
    Reserva(id = 3, nombreAlbergue = "Albergue 3", nombreResponsable = "Juan", apellidoResponsable = "Perez", celular = 8113844318, fechaLlegada = "23-10-2025", fechaSalida = "26-10-2025", numPersonas = 8)
)