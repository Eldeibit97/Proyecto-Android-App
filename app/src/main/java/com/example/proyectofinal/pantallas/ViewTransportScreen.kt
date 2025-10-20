package com.example.proyectofinal.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectofinal.componentes.TopBar

data class PersonalInfo(
    val nombre: String = "Jesús Alberto",
    val nombreChofer: String = "Daniel Gonzalez",
    val apellido: String = "Jiménez Paz",
    val telefono: String = "+52 81 1234 5678",
    val origen: String = "Av. Constitución 123, Centro, Monterrey",
    val destino: String = "Posada del Peregrino, Av. Simón Bolívar 190, Monterrey",
    val fecha: String = "16/Octubre/2025",
    val hora: String = "17:45",
    val personas: Int = 3
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewTransportation(
    info: PersonalInfo = PersonalInfo(),
    onOpenDrawer: () -> Unit = {},
    title: String = "Detalles del viaje"
) {
    val scroll = rememberScrollState()
    val personas: Int = 3

    Scaffold(
        topBar = { TopBar(onDrawerClick = onOpenDrawer, title = title) } // Usa tu TopBar existente
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scroll)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Outlined.Check,
                        contentDescription = "Icono de check",
                        modifier = Modifier.size(25.dp),
                        tint = Color(0xFF4CAF50)
                    )
                    Text(text = "Transporte confirmado",
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.padding(6.dp))
                Text(text = "Nuestro chofer los buscará en el lugar y hora designados.",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify)
                Spacer(modifier = Modifier.padding(6.dp))
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)

            ){
                Column(modifier = Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)) {
                        Text("Información personal", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Column(Modifier.padding(12.dp)) {
                                // Nombre
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Outlined.Person,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            "Nombre",
                                            fontSize = 15.sp,
                                            color = Color.Gray,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = if (info.nombre.isBlank()) "—" else info.nombre,
                                            fontSize = 20.sp
                                        )
                                    }
                                }

                                Divider(Modifier.padding(vertical = 8.dp))

                                // Apellido
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Outlined.Person,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            "Apellido",
                                            fontSize = 15.sp,
                                            color = Color.Gray,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = if (info.apellido.isBlank()) "—" else info.apellido,
                                            fontSize = 20.sp
                                        )
                                    }
                                }

                                Divider(Modifier.padding(vertical = 8.dp))

                                // Teléfono
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Outlined.Phone,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            "Teléfono",
                                            fontSize = 15.sp,
                                            color = Color.Gray,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = if (info.telefono.isBlank()) "—" else info.telefono,
                                            fontSize = 20.sp
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(Modifier.height(10.dp))
                        Text("Viaje", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Spacer(Modifier.height(4.dp))
                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Column(Modifier.padding(12.dp)) {
                                // Origen
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Outlined.LocationOn,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            "Origen",
                                            fontSize = 15.sp,
                                            color = Color.Gray,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = if (info.origen.isBlank()) "—" else info.origen,
                                            fontSize = 20.sp
                                        )
                                    }
                                }
                                Divider(Modifier.padding(vertical = 8.dp))
                                // Destino
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Outlined.LocationOn,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            "Destino",
                                            fontSize = 15.sp,
                                            color = Color.Gray,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = if (info.destino.isBlank()) "—" else info.destino,
                                            fontSize = 20.sp
                                        )
                                    }
                                }
                                Divider(Modifier.padding(vertical = 8.dp))
                                // Fecha
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Outlined.CalendarMonth,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            "Fecha",
                                            fontSize = 15.sp,
                                            color = Color.Gray,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = if (info.fecha.isBlank()) "—" else info.fecha,
                                            fontSize = 20.sp
                                        )
                                    }
                                }
                                Divider(Modifier.padding(vertical = 8.dp))
                                // Hora
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Outlined.AccessTime,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            "Hora",
                                            fontSize = 15.sp,
                                            color = Color.Gray,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = if (info.hora.isBlank()) "—" else info.hora,
                                            fontSize = 20.sp
                                        )
                                    }
                                }
                                Divider(Modifier.padding(vertical = 8.dp))
                                // Personas
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Outlined.PersonOutline,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            "Personas",
                                            fontSize = 15.sp,
                                            color = Color.Gray,
                                            fontWeight = FontWeight.SemiBold
                                        )

                                        Text(
                                            text = personas.toString(),
                                            fontSize = 20.sp
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(Modifier.height(10.dp))
                        Text("Chofer", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Spacer(Modifier.height(4.dp))
                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Column(Modifier.padding(12.dp)) {
                                // Nombre
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Outlined.Person,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            "Nombre",
                                            fontSize = 15.sp,
                                            color = Color.Gray,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = if (info.nombreChofer.isBlank()) "—" else info.nombreChofer,
                                            fontSize = 20.sp
                                        )
                                    }
                                }

                                Divider(Modifier.padding(vertical = 8.dp))

                                // Teléfono
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Outlined.Phone,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            "Teléfono",
                                            fontSize = 15.sp,
                                            color = Color.Gray,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = if (info.telefono.isBlank()) "—" else info.telefono,
                                            fontSize = 20.sp
                                        )
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun PreviewViewTransportation() {
    MaterialTheme {
        ViewTransportation()
    }
}
