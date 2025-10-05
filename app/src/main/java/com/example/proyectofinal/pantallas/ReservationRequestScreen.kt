package com.example.proyectofinal.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.R
import com.example.proyectofinal.componentes.AlbergueInfoCard
import com.example.proyectofinal.componentes.MapsCard
import com.example.proyectofinal.modelos.Albergue
import com.example.proyectofinal.modelos.getAlbergues

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationRequestScreen(
    albergue: Albergue = Albergue(),
    albergueName: String = albergue.nombre,
    ratingText: String = "5.0 (4)",
    hostTag: String = "Superanfitrión",
    dates: String = "21–23 de nov de 2025",
    participants: String = "1 adulto",
    price: String = "$3,365.72, impuestos incluidos",
    onModifyDates: () -> Unit = {},
    onModifyParticipants: () -> Unit = {},
    onShowPriceDetails: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            // Botón fijo en la parte inferior
            Surface(shadowElevation = 6.dp) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = onNext,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = "Siguiente", fontSize = 18.sp)
                    }
                }
            }
        }
    ) { innerPadding ->
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Detalles de la Reserva",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(12.dp))
            // Card principal con detalle de reservación
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    /*Text(
                        text = "Detalles de la Reserva",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                     */
                    // Imagen (ajusta el drawable a tu recurso: R.drawable.reserva_posada)
                    Image(
                        painter = painterResource(id = R.drawable.pdp),
                        contentDescription = "Foto del albergue",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)) {
                        // Título y rating
                        Text(
                            text = albergue.nombre,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "★ $ratingText", fontSize = 13.sp)
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(text = "• $hostTag", fontSize = 13.sp)
                        }

                        Divider(modifier = Modifier.padding(vertical = 12.dp))

                        // Fechas
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = "Fechas", fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(text = dates, fontSize = 14.sp)
                            }
                            OutlinedButton(onClick = onModifyDates, modifier = Modifier.width(100.dp)) {
                                Text(text = "Editar")
                            }
                        }

                        Divider(modifier = Modifier.padding(vertical = 8.dp))

                        // Participantes
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = "Participantes", fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(text = participants, fontSize = 14.sp)
                            }
                            OutlinedButton(onClick = onModifyParticipants, modifier = Modifier.width(100.dp)) {
                                Text(text = "Editar")
                            }
                        }

                        Divider(modifier = Modifier.padding(vertical = 8.dp))

                        // Precio
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = "Precio total", fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(text = price, fontSize = 14.sp)
                            }
                            OutlinedButton(onClick = onShowPriceDetails, modifier = Modifier.width(100.dp)) {
                                Text(text = "Detalles")
                            }
                        }

                        Divider(modifier = Modifier.padding(vertical = 8.dp))

                        // Política de cancelación
                        Text(text = "Cancelación gratuita", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Si cancelas la reservación antes del 20 nov, recibirás un reembolso completo.",
                            fontSize = 13.sp
                        )
                        Text(
                            text = "Política completa",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .clickable { /* abrir política */ }
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }

            // Espacio para separar card del footer
            Spacer(modifier = Modifier.height(20.dp))

            // Información adicional o resumen (opcional)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(text = "Resumen", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Revisa los detalles antes de continuar. Al avanzar confirmarás la solicitud de reserva.")
                }
            }

            Spacer(modifier = Modifier.height(80.dp)) // deja espacio para el botón fijo
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun PreviewReservationRequestScreen() {
    ReservationRequestScreen(
        onModifyDates = { /* preview */ },
        onModifyParticipants = { /* preview */ },
        onShowPriceDetails = { /* preview */ },
        onNext = { /* preview siguiente */ }
    )
}
