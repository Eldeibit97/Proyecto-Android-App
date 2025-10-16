package com.example.proyectofinal.navegacion

sealed class ScreenNames(val route : String) {
    object Login : ScreenNames("login")
    object Home : ScreenNames("home")
    object Reservation : ScreenNames("reservation/{id}") {
        fun createRoute(id: Int) = "reservation/$id"
    }
    object ViewReservation : ScreenNames("viewreservation")
    object Location : ScreenNames("location")
    object TransportRequest : ScreenNames("transport")
    object Profile : ScreenNames("profile")
    object News : ScreenNames("news")
}