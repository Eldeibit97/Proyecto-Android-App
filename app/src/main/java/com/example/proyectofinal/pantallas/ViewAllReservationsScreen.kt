package com.example.proyectofinal.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinal.componentes.TopBar
import kotlinx.coroutines.launch
import com.example.proyectofinal.modelos.Reserva
import com.example.proyectofinal.modelos.TransporteReservation
import com.example.proyectofinal.navegacion.ScreenNames
import com.example.proyectofinal.ui.components.PosadaCard
import com.example.proyectofinal.ui.components.TransporteCard

@Composable
fun ViewAllReservationsScreen (navController: NavController,
                               aHome: () -> Unit = {},
                               aViaje: () -> Unit = {},
                               aLogin: () -> Unit = {},
                               aReservas: () -> Unit = {},
                               aNoticias: () -> Unit = {}){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val posadas = listOf(
        Reserva(
            id = 1,
            nombreAlbergue = "Caritas Monterrey",
            nombreResponsable = "Juan",
            apellidoResponsable = "Perez",
            celular = 8123456789,
            fechaLlegada = "1 Ene 2023",
            fechaSalida = "2 Ene 2023",
            numPersonas = 2
        )
    )
    val transportes = listOf(
        TransporteReservation(
            10,
            "Central Camionera",
            "Caritas Monterrey",
            "1 Ene 2023, 08:30 AM",
            2
        )
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(150.dp)) {
                Text("Opciones", modifier = Modifier.padding(16.dp))
                NavigationDrawerItem(
                    label = { Text("Home") },
                    selected = false,
                    onClick = { aHome() }
                )
                NavigationDrawerItem(
                    label = { Text("Viaje") },
                    selected = false,
                    onClick = { aViaje() }
                )
                NavigationDrawerItem(
                    label = {Text(text = "Reservas")},
                    selected = true,
                    onClick = { aReservas() }
                )
                NavigationDrawerItem(
                    label = { Text("Noticias") },
                    selected = false,
                    onClick = { aNoticias() }
                )
                NavigationDrawerItem(
                    label = { Text("Cerrar Sesion") },
                    selected = false,
                    onClick = { aLogin() }
                )
            }
        }
    ) {
        Scaffold(topBar = {
            TopBar(
                onDrawerClick = { scope.launch { drawerState.open() } },
                title = "Reservaciones"
            )
        }
        ) { innerPadding ->
            Column(modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 15.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Outlined.CalendarToday,
                            contentDescription = "Icono de notificaciones",
                            modifier = Modifier.size(25.dp),
                            tint = Color(0xFF00A6FF)
                        )
                        Text(text = "Tus reservaciones",
                            modifier = Modifier.padding(horizontal = 8.dp),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.padding(6.dp))
                    Text(text = "Revisa y gestiona las reservas que has realizado",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Justify)

                    Spacer(modifier = Modifier.padding(8.dp))

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 24.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        if (posadas.isNotEmpty()) {
                            stickyHeader {
                                // ✅ Header personalizado para sección POSADA
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color(0xFFF7FAFE))
                                        .padding(horizontal = 12.dp, vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Posada",
                                        style = MaterialTheme.typography.titleSmall.copy(fontSize = 25.sp),
                                    )
                                }
                            }
                            items(posadas, key = { it.id }) { reserva ->
                                PosadaCard(reserva = reserva, onClick = {navController.navigate(
                                    ScreenNames.ViewReservation.createRoute(reserva.id)
                                )})
                            }
                            item { Spacer(modifier = Modifier.height(8.dp)) }
                        }

                        if (transportes.isNotEmpty()) {
                            stickyHeader {
                                // ✅ Header personalizado para sección TRANSPORTE
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color(0xFFF7FAFE))
                                        .padding(horizontal = 12.dp, vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Transporte",
                                        style = MaterialTheme.typography.titleSmall.copy(fontSize = 25.sp),
                                    )
                                }
                            }
                            items(transportes, key = { it.id }) { reserva ->
                                TransporteCard(reserva = reserva, onClick = { navController.navigate(
                                    ScreenNames.ViewTransport.createRoute(reserva.id)
                                ) })
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewViewAllReservationsScreen() {
    val nav = rememberNavController()
    ViewAllReservationsScreen(navController = nav)
}