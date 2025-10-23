package com.example.proyectofinal.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CarCrash
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Hotel
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectofinal.componentes.TopBar
import com.example.proyectofinal.modelos.PersonalInfo
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewTransportationScreen(
    info: PersonalInfo = PersonalInfo(),aHome: () -> Unit = {},
    aViaje: () -> Unit = {}, aLogin: () -> Unit = {},
    aReservas: () -> Unit = {}, aNoticias: () -> Unit = {},
    onRegresar: () -> Unit = {}, id: Int
) {
    val scroll = rememberScrollState()
    val personas = 3
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(150.dp)) {
                Text("Opciones", modifier = Modifier.padding(16.dp))
                NavigationDrawerItem(
                    label = { Text("Home") },
                    icon = { Icon(imageVector = Icons.Outlined.Home,
                        contentDescription = "Celular",
                        modifier = Modifier.size(17.dp))},
                    selected = false, onClick = { aHome() },
                    shape = RoundedCornerShape(0.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Viaje") },
                    selected = false, onClick = { aViaje() },
                    icon = { Icon(imageVector = Icons.Outlined.CarCrash,
                        contentDescription = "Celular",
                        modifier = Modifier.size(17.dp))},
                    shape = RoundedCornerShape(0.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Reservas") },
                    selected = false, onClick = { aReservas() },
                    icon = { Icon(imageVector = Icons.Outlined.Hotel,
                        contentDescription = "Celular",
                        modifier = Modifier.size(17.dp))},
                    shape = RoundedCornerShape(0.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Noticias") },
                    selected = false, onClick = { aNoticias() },
                    icon = { Icon(imageVector = Icons.Outlined.Newspaper,
                        contentDescription = "Celular",
                        modifier = Modifier.size(17.dp))},
                    shape = RoundedCornerShape(0.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Cerrar Sesión") },
                    selected = false, onClick = { aLogin() },
                    icon = { Icon(imageVector = Icons.Outlined.Logout,
                        contentDescription = "Celular",
                        modifier = Modifier.size(17.dp))},
                    shape = RoundedCornerShape(0.dp)
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    onDrawerClick = { scope.launch { drawerState.open() } },
                    title = "Detalles del Viaje"
                )
            }
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = "Icono de check",
                            modifier = Modifier.size(25.dp),
                            tint = Color(0xFF4CAF50)
                        )
                        Text(
                            text = "Transporte confirmado",
                            modifier = Modifier.padding(horizontal = 8.dp),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.padding(6.dp))
                    Text(
                        text = "Nuestro chofer los buscará en el lugar y hora designados.",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Justify
                    )
                    Spacer(modifier = Modifier.padding(6.dp))
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)

                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)) {
                            Text(
                                "Información personal",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
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
                                    HorizontalDivider(Modifier.padding(vertical = 8.dp))
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
                                    HorizontalDivider(Modifier.padding(vertical = 8.dp))
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
                                    HorizontalDivider(Modifier.padding(vertical = 8.dp))
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
                                    HorizontalDivider(Modifier.padding(vertical = 8.dp))
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
                                    HorizontalDivider(Modifier.padding(vertical = 8.dp))
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
                                    HorizontalDivider(Modifier.padding(vertical = 8.dp))
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
                                    HorizontalDivider(Modifier.padding(vertical = 8.dp))
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = onRegresar,
                        modifier = Modifier.fillMaxWidth(0.5f),
                        enabled = true,
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(text = "Regresar")
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
        ViewTransportationScreen(id = 1)
    }
}
