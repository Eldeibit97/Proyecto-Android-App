package com.example.proyectofinal.navegacion

sealed class ScreenNames(val route : String) {
    object Login : ScreenNames(route = "login")
    object Home : ScreenNames(route = "home")
    object Reservation : ScreenNames(route = "reservation/{id}") {
        fun createRoute(id: Int) = "reservation/$id"
    }
    object ViewReservation : ScreenNames(route = "viewreservation)")
    object ViewAllResevations : ScreenNames(route = "viewallreservations")
    object Location : ScreenNames(route = "location")
    object TransportRequest : ScreenNames(route = "transport")

    object ViewTransport : ScreenNames(route = "transportView")
    object Profile : ScreenNames(route = "profile")
    object News : ScreenNames(route = "news")

}