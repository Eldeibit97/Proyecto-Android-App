package com.example.proyectofinal.navegacion

sealed class ScreenNames(val route : String) {
    object Login : ScreenNames(route = "login")
    object Home : ScreenNames(route = "home")
    object Reservation : ScreenNames(route = "reservation/{id}") {
        fun createRoute(id: Int) = "reservation/$id"
    }
    object ViewReservation : ScreenNames(route = "viewReservation/{id}"){
        fun createRoute(id: String) = "viewReservation/$id"
    }

    object ConfirmationReservation : ScreenNames(route = "confirmReservation") {
        fun createRoute(
            nombre: String,
            apellido: String,
            celular: String,
            fechaLlegada: String,
            fechaSalida: String,
            numPersonas: Int
        ): String {
            return "confirmReservation?" +
                    "nombre=${nombre}&" +
                    "apellido=${apellido}&" +
                    "celular=${celular}&" +
                    "fechaLlegada=${fechaLlegada}&" +
                    "fechaSalida=${fechaSalida}&" +
                    "numPersonas=${numPersonas}"
        }
    }


    object ConfirmationTransport : ScreenNames(route = "confirmTransport/{id}")


    object ViewAllReservations : ScreenNames(route = "viewallreservations")
    object TransportRequest : ScreenNames(route = "transport")

    object ViewTransport : ScreenNames(route = "viewTransport/{id}"){
        fun createRoute(id: String) = "viewTransport/$id"
    }
    object Profile : ScreenNames(route = "profile")
    object News : ScreenNames(route = "news")

}