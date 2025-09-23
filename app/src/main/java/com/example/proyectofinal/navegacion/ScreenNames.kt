package com.example.proyectofinal.navegacion

sealed class ScreenNames(val route : String) {
    object Login : ScreenNames("login")
    object Home : ScreenNames("home/{id}") {
        fun createRoute(id: Int) = "home/$id"
    }
    object Reservation : ScreenNames("reservation")
    object Profile : ScreenNames("profile")
    object News : ScreenNames("news")
}