package com.example.proyectofinal.navegacion

sealed class ScreenNames(val route : String) {
    object Login : ScreenNames(route = "login")
    object Home : ScreenNames(route = "home")
    object Reservation : ScreenNames(route = "reservation/{id}") {
        fun createRoute(id: Int) = "reservation/$id"
    }
    object ViewReservation : ScreenNames(route = "viewReservation/{id}"){
        fun createRoute(id: Int) = "viewReservation/$id"
    }
    object ViewAllReservations : ScreenNames(route = "viewallreservations")
    object TransportRequest : ScreenNames(route = "transport")

    object ViewTransport : ScreenNames(route = "viewTransport/{id}"){
        fun createRoute(id: Int) = "viewTransport/$id"
    }
    object Profile : ScreenNames(route = "profile")
    object News : ScreenNames(route = "news")

}