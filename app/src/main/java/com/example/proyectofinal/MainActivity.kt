package com.example.proyectofinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyectofinal.navegacion.ScreenNames
import com.example.proyectofinal.pantallas.HomeScreen
import com.example.proyectofinal.pantallas.LocationScreen
import com.example.proyectofinal.pantallas.LoginScreen
import com.example.proyectofinal.pantallas.ReservaTransporteScreen
import com.example.proyectofinal.pantallas.ReservationRequestScreen
import com.example.proyectofinal.modelos.getAlbergues
import com.example.proyectofinal.pantallas.ReservationConfirmationScreen
import com.example.proyectofinal.ui.theme.ProyectoFinalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoFinalTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun App(modifier: Modifier = Modifier){
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = ScreenNames.Login.route){
        composable(route = ScreenNames.Login.route) {
            LoginScreen(aHome = {nav.navigate(ScreenNames.Home.route)})
        }
        composable(route = ScreenNames.Home.route){
            HomeScreen(albergue = getAlbergues(),
                aTransport = { nav.navigate(ScreenNames.Location.route)},
                aReservation = { nav.navigate(ScreenNames.Reservation.createRoute(it.id)) },
                aReservas = { nav.navigate(ScreenNames.ViewReservation.route) },
                aViaje = {nav.navigate(ScreenNames.TransportRequest.route)},
                aLogin = {nav.navigate(ScreenNames.Login.route)})
        }
        composable(route = ScreenNames.TransportRequest.route){
            ReservaTransporteScreen(onAvanzar = {},
                aReservas = { nav.navigate(ScreenNames.ViewReservation.route) },
                aViaje = {nav.navigate(ScreenNames.TransportRequest.route)},
                aLogin = {nav.navigate(ScreenNames.Login.route)})
        }
        composable(route = ScreenNames.Reservation.route,
            arguments = listOf(navArgument("id"){type = NavType.IntType})){
            val id = it.arguments?.getInt("id") ?: 0
            val albergue = getAlbergues().firstOrNull(){it.id == id}
            ReservationRequestScreen(albergue = albergue,
                onRegresar = {nav.popBackStack()},
                aHome = {nav.navigate(ScreenNames.Home.route)},
                aViaje = {nav.navigate(ScreenNames.TransportRequest.route)},
                aLogin = {nav.navigate(ScreenNames.Login.route)},
                onReservar = {nav.navigate(ScreenNames.ViewReservation.route)})
        }
        composable(route = ScreenNames.ViewReservation.route){
            ReservationConfirmationScreen(
                onRegresar = {nav.navigate(ScreenNames.Home.route)},
                aHome = {nav.navigate(ScreenNames.Home.route)},
                aViaje = {nav.navigate(ScreenNames.TransportRequest.route)},
                aLogin = {nav.navigate(ScreenNames.Login.route)}
            )
        }
        composable(route = ScreenNames.ViewAllResevations.route){

        }
        composable(route = ScreenNames.Location.route){
            LocationScreen(aTaxi = { nav.navigate(ScreenNames.TransportRequest.route)})
        }
        composable(route = ScreenNames.News.route){

        }
        composable(route = ScreenNames.Profile.route){}
    }
}

