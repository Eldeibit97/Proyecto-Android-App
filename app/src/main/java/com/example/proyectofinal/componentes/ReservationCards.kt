package com.example.proyectofinal.ui.components

// Este archivo crea las tarjetas que se muestran en la pantalla de reservas. Reservas de albergue y de transporte.


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Commute
import androidx.compose.material.icons.outlined.Hotel
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyectofinal.modelos.PersonalInfo
import com.example.proyectofinal.modelos.Reserva

@Composable
fun PosadaCard(
    reserva: Reserva,
    modifier: Modifier = Modifier,
    onClick: (Reserva) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(reserva) },
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.Hotel, contentDescription = null, tint = Color(0xFF00A6FF))
                Spacer(Modifier.width(8.dp))
                Text(
                    text = reserva.nombreAlbergue,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.weight(1f))
            }
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.Schedule, contentDescription = null, tint = Color.Gray)
                Spacer(Modifier.width(6.dp))
                Text("${reserva.fechaLlegada} → ${reserva.fechaSalida}", color = Color.Gray)
            }
            Spacer(Modifier.height(6.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.People, contentDescription = null, tint = Color.Gray)
                Spacer(Modifier.width(6.dp))
                Text("${reserva.numPersonas} huésped(es)", color = Color.Gray)
            }
        }
    }
}

@Composable
fun TransporteCard(
    reserva: PersonalInfo,
    modifier: Modifier = Modifier,
    onClick: (PersonalInfo) -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(reserva) },
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.Commute, contentDescription = null, tint = Color(0xFF00A6FF))
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "${reserva.origen} → ${reserva.destino}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.weight(1f))
            }
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.Schedule, contentDescription = null, tint = Color.Gray)
                Spacer(Modifier.width(6.dp))
                Text(reserva.fecha, color = Color.Gray)
            }
            Spacer(Modifier.height(6.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Outlined.People, contentDescription = null, tint = Color.Gray)
                Spacer(Modifier.width(6.dp))
                Text("${reserva.personas} pasajero(s)", color = Color.Gray)
            }
        }
    }
}

/**
@Preview(showBackground = true)
@Composable
fun PreviewPosadaCard() {
    val demo = Reserva(
        id = 1,
        nombreAlbergue = "Posada San Miguel",
        fechaLlegada = "12 Oct 2025",
        fechaSalida = "14 Oct 2025",
        numPersonas = 2
    )
    PosadaCard(reserva = demo)
}

@Preview(showBackground = true)
@Composable
fun PreviewTransporteCard() {
    val demo = TransporteConfirmation(
        id = 10,
        origen = "Campus Tec",
        destino = "Posada San Miguel",
        fecha = "12 Oct 2025, 08:30",
        pasajeros = 3
    )
    TransporteCard(reserva = demo)
}
**/