package com.example.proyectofinal.navegacion

sealed class ScreenNames(val routers : String) {
    object Login : ScreenNames("login")
    object Home : ScreenNames("home/{id}") {
        fun createRoute(id: Int) = "home/$id"
    }
}