package com.example.proyectofinal.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun MapsCard(){
    Card(modifier = Modifier.fillMaxWidth().padding(15.dp)){
        Text(text = "aqui ira el mapa")
    }
}