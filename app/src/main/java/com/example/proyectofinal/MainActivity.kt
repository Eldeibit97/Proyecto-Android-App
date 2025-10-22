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
import com.example.proyectofinal.pantallas.LoginScreen
import com.example.proyectofinal.pantallas.ReservaTransporteScreen
import com.example.proyectofinal.pantallas.ReservationRequestScreen
import com.example.proyectofinal.modelos.getAlbergues
import com.example.proyectofinal.pantallas.NewsScreen
import com.example.proyectofinal.pantallas.ReservationConfirmationScreen
import com.example.proyectofinal.pantallas.ViewAllReservationsScreen
import com.example.proyectofinal.pantallas.ViewTransportationScreen
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
            HomeScreen(albergues = getAlbergues(),
                aTransport = { nav.navigate(ScreenNames.TransportRequest.route)},
                aReservation = { nav.navigate(ScreenNames.Reservation.createRoute(it.id)) },
                aReservas = { nav.navigate(ScreenNames.ViewAllReservations.route) },
                aViaje = {nav.navigate(ScreenNames.TransportRequest.route)},
                aNoticias = { nav.navigate(ScreenNames.News.route)},
                aLogin = {nav.navigate(ScreenNames.Login.route)})
        }
        composable(route = ScreenNames.TransportRequest.route){
            ReservaTransporteScreen(solicitar = {nav.navigate(ScreenNames.ViewTransport.route)},
                aHome = {nav.navigate(ScreenNames.Home.route)},
                aReservas = { nav.navigate(ScreenNames.ViewReservation.route) },
                aViaje = {nav.navigate(ScreenNames.TransportRequest.route)},
                aNoticias = { nav.navigate(ScreenNames.News.route) },
                aLogin = {nav.navigate(ScreenNames.Login.route)})
        }
        composable(route = ScreenNames.Reservation.route,
            arguments = listOf(navArgument("id"){type = NavType.IntType})){
            val id = it.arguments?.getInt("id") ?: 0
            val albergue = getAlbergues().firstOrNull{it.id == id}
            ReservationRequestScreen(albergue = albergue,
                onRegresar = {nav.popBackStack()},
                aHome = {nav.navigate(ScreenNames.Home.route)},
                aViaje = {nav.navigate(ScreenNames.TransportRequest.route)},
                aNoticias = { nav.navigate(ScreenNames.News.route)},
                aLogin = {nav.navigate(ScreenNames.Login.route)},
                onReservar = {nav.navigate(ScreenNames.ViewReservation.route)})
        }
        composable(route = ScreenNames.ViewReservation.route){ backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: 0
            ReservationConfirmationScreen(
                id = id,
                onRegresar = {nav.navigate(ScreenNames.Home.route)},
                aHome = {nav.navigate(ScreenNames.Home.route)},
                aViaje = {nav.navigate(ScreenNames.TransportRequest.route)},
                aNoticias = { nav.navigate(ScreenNames.News.route)},
                aLogin = {nav.navigate(ScreenNames.Login.route)}
            )
        }
        composable(route = ScreenNames.ViewAllReservations.route){
            ViewAllReservationsScreen(navController = nav,
                aHome = {nav.navigate(ScreenNames.Home.route)},
                aReservas = { nav.navigate(ScreenNames.ViewReservation.route) },
                aViaje = {nav.navigate(ScreenNames.TransportRequest.route)},
                aNoticias = { nav.navigate(ScreenNames.News.route)},
                aLogin = {nav.navigate(ScreenNames.Login.route)})
        }
        composable(route = ScreenNames.News.route){
            NewsScreen(
                aHome = {nav.navigate(ScreenNames.Home.route)},
                aReservas = { nav.navigate(ScreenNames.ViewReservation.route) },
                aViaje = {nav.navigate(ScreenNames.TransportRequest.route)},
                aNoticias = { nav.navigate(ScreenNames.News.route)},
                aLogin = {nav.navigate(ScreenNames.Login.route)})
        }
        composable(route = ScreenNames.Profile.route){}
        composable(route = ScreenNames.ViewTransport.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ){ backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            ViewTransportationScreen(id = id, onRegresar = {nav.navigate(ScreenNames.Home.route)},
                aHome = {nav.navigate(ScreenNames.Home.route)},
                aReservas = { nav.navigate(ScreenNames.ViewReservation.route) },
                aViaje = {nav.navigate(ScreenNames.TransportRequest.route)},
                aNoticias = { nav.navigate(ScreenNames.News.route)},
                aLogin = {nav.navigate(ScreenNames.Login.route)})
        }
    }
}

