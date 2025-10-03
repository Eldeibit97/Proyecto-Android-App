package com.example.proyectofinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinal.navegacion.ScreenNames
import com.example.proyectofinal.pantallas.HomeScreen
import com.example.proyectofinal.pantallas.LoginScreen
import com.example.proyectofinal.pantallas.ReservaTransporteScreen
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
            HomeScreen(/*aTransport = { nav.navigate(ScreenNames.TransportRequest.route)}*/)

        }
        composable(route = ScreenNames.Home.route){

        }
    }
}

