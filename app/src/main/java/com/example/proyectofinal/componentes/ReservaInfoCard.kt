package com.example.proyectofinal.componentes

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectofinal.modelos.Albergue
import com.example.proyectofinal.modelos.Reserva
import com.example.proyectofinal.modelos.getReservas

@Preview(showBackground = true)
@Composable
fun ReservaInfoCard(reserva: Reserva = getReservas()[2], verReserva: (Albergue) -> Unit = {}){
    Card(){

    }
}