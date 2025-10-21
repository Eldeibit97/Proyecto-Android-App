package com.example.proyectofinal.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.modelos.Albergue

@Preview(showBackground = true)
@Composable
fun PrecioServicioCard(albergue: Albergue? = Albergue()){
    Card(modifier = Modifier
        .fillMaxWidth().padding(horizontal = 15.dp)) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Informacion de servicios",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)
            Column(modifier = Modifier.padding(vertical = 4.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                albergue?.servicios?.forEach { servicio ->
                    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)){
                        Text(text = servicio.nombre,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold)
                        Spacer(modifier = Modifier.weight(1f))
                        if(servicio.precio == "Gratis") {
                            Text(
                                text = servicio.precio,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }else{
                            Text(
                                text = "$${servicio.precio}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
            Text(text = "Esta informción es para que conozca los servicios que proporciona el albergue."+
                    " En caso de requerir de alguno durante su estancia, entre a los detalles de su" +
                    "reserva al terminar para solicitar los servicios",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 12.sp, textAlign = TextAlign.Justify)
        }
    }
}