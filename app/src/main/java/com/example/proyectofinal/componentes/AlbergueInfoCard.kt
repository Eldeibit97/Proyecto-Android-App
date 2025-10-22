package com.example.proyectofinal.componentes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material.icons.outlined.ArrowCircleRight
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.WatchLater
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.proyectofinal.R
import com.example.proyectofinal.modelos.Albergue
import com.example.proyectofinal.modelos.getAlbergues
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Preview(showBackground = true)
@Composable
fun AlbergueInfoCard(albergue: Albergue = getAlbergues()[1],
                     aSolicitarViaje: () -> Unit = {},
                     aReservar: (Albergue) -> Unit = {}){
    var showDialog by remember { mutableStateOf(false) }
    // 📍 Coordenadas de los tres albergues
    val posada = LatLng(albergue.latitud, albergue.longitud)

    // 📍 Posición inicial de la cámara (centrada en Monterrey)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(posada, 11.5f)
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Card(shape = RoundedCornerShape(16.dp)) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp)
                ) {
                    GoogleMap(
                        modifier = Modifier.fillMaxWidth().height(300.dp),
                        cameraPositionState = cameraPositionState
                    ) {
                        // 📍 Marker 1: Posada del Peregrino
                        MarkerInfoWindow(
                            state = rememberMarkerState(position = posada),
                            title = "Posada del Peregrino",
                            snippet = "Albergue en Monterrey"
                        )
                    }

                }
                Row(modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 8.dp)
                    .height(height = 50.dp)
                    .width(width = 10.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(onClick = aSolicitarViaje,
                        enabled = true,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = "Solicitar transporte",
                            modifier = Modifier.padding(vertical = 3.dp, horizontal = 5.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = albergue.nombre,
                modifier = Modifier.padding(vertical = 3.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center)
            Card(colors = CardColors(Color(color = 0xFF3CB93A),
                Color(color = 0xFFFFFFFF),
                Color(color = 0xFFEF3F3F),
                Color(color = 0xFFFFFFFF)),
                shape = RoundedCornerShape(size = 4.dp)
            ){
                Text(text = "Disponible",
                    modifier = Modifier.padding(vertical = 3.dp, horizontal = 5.dp),
                    fontSize = 16.sp)
            }
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            Image(painter = painterResource(id = R.drawable.pdp),
                modifier = Modifier
                    .fillMaxWidth()
                    .width(width = 200.dp)
                    .clip(shape = RoundedCornerShape(10.dp)),
                contentDescription = "Foto del albergue")
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically){
                Icon(imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "Direccion",
                    modifier = Modifier.size(15.dp))
                Text(text = albergue.direccion,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp))
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically){
                Icon(imageVector = Icons.Outlined.Phone,
                    contentDescription = "Celular",
                    modifier = Modifier.size(15.dp))
                Text(text = "+52 ${albergue.celular}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp))
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically){
                Icon(imageVector = Icons.Outlined.WatchLater,
                    contentDescription = "Horario",
                    modifier = Modifier.size(15.dp))
                Text(text = "Todos los dias, a cualquier hora.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp))
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically){
                Icon(imageVector = Icons.Outlined.People,
                    contentDescription = "Cupo",
                    modifier = Modifier.size(15.dp))
                Text(text = "${albergue.disponibilidad}/${albergue.capacidad}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp))
            }
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically){
                Text(text = "Servicios:",
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold)
            }
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                albergue.servicios.forEach { servicio ->
                    Card(modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                        shape = RoundedCornerShape(size = 6.dp),
                        colors = CardColors(MaterialTheme.colorScheme.onTertiary,
                            MaterialTheme.colorScheme.onBackground,
                            MaterialTheme.colorScheme.onTertiary,
                            MaterialTheme.colorScheme.onBackground),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)){
                        Text(text = servicio.nombre,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 8.dp)
            .height(height = 35.dp),
            horizontalArrangement = Arrangement.Center){
            Button(onClick = { showDialog = true },
                modifier = Modifier.width(width = 180.dp),
                colors = ButtonColors(MaterialTheme.colorScheme.onTertiary,
                    MaterialTheme.colorScheme.onBackground,
                    MaterialTheme.colorScheme.onTertiary,
                    MaterialTheme.colorScheme.onBackground),
                contentPadding = PaddingValues(horizontal = 10.dp,vertical = 2.dp),
                shape = RoundedCornerShape(5.dp)){
                Icon(imageVector = Icons.Outlined.ArrowCircleRight,
                    contentDescription = "Cómo llegar",
                    modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "Cómo llegar",
                    fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.padding(all = 2.dp))
            Button(onClick = { aReservar(albergue) },
                modifier = Modifier.width(width = 180.dp),
                contentPadding = PaddingValues(horizontal = 10.dp,vertical = 2.dp),
                shape = RoundedCornerShape(size = 5.dp)){
                Icon(imageVector = Icons.Filled.CalendarToday,
                    contentDescription = "Hacer reserva",
                    modifier = Modifier.size(size = 20.dp))
                Spacer(modifier = Modifier.padding(all = 5.dp))
                Text(text = "Reservar",
                    fontSize = 18.sp)
            }
        }
    }
}