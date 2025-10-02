package com.example.proyectofinal.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.componentes.AlbergueInfoCard
import com.example.proyectofinal.componentes.MapsCard
import com.example.proyectofinal.modelos.getAlbergues

@Preview(showBackground = true)
@Composable
fun HomeScreen(){
    val albergues = getAlbergues()
    Column(modifier = Modifier.fillMaxSize()){
        MapsCard()
        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 2.dp)){
            Text(text = "Ubicaciones disponibles ()",
                modifier = Modifier.padding(5.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp)
            Text(text = "Selecciona una ubicación para realizar una reserva",
                modifier = Modifier.padding(5.dp),
                fontSize = 20.sp)
        }
        LazyColumn(){
            items(items = albergues){
                albergue -> AlbergueInfoCard(albergue)
            }
        }
    }
}