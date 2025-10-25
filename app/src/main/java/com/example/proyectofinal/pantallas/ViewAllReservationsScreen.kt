package com.example.proyectofinal.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.CarCrash
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Hotel
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.proyectofinal.componentes.TopBar
import com.example.proyectofinal.modelos.PersonalInfo
import kotlinx.coroutines.launch
import com.example.proyectofinal.modelos.Reserva
import com.example.proyectofinal.modelos.fetchReservas
import com.example.proyectofinal.modelos.fetchTransporte
import com.example.proyectofinal.navegacion.ScreenNames
import com.example.proyectofinal.ui.components.PosadaCard
import com.example.proyectofinal.ui.components.TransporteCard

@Composable
fun ViewAllReservationsScreen(
    navController: NavController,
    aHome: () -> Unit = {},
    aViaje: () -> Unit = {},
    aLogin: () -> Unit = {},
    aReservas: () -> Unit = {},
    aNoticias: () -> Unit = {}
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // 🔹 Estados de datos
    var reservas by remember { mutableStateOf<List<Reserva>>(emptyList()) }
    var transporte by remember { mutableStateOf<List<PersonalInfo>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()

    // 🔹 Carga única de datos del usuario autenticado
    LaunchedEffect(Unit) {
        reservas = fetchReservas()
        transporte = fetchTransporte()
        isLoading = false
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(150.dp)) {
                Text("Opciones", modifier = Modifier.padding(16.dp))
                NavigationDrawerItem(
                    label = { Text("Home") },
                    icon = { Icon(Icons.Outlined.Home, contentDescription = "Home", modifier = Modifier.size(17.dp)) },
                    selected = false, onClick = aHome,
                    shape = RoundedCornerShape(0.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Viaje") },
                    icon = { Icon(Icons.Outlined.CarCrash, contentDescription = "Viaje", modifier = Modifier.size(17.dp)) },
                    selected = false, onClick = aViaje,
                    shape = RoundedCornerShape(0.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Reservas") },
                    icon = { Icon(Icons.Outlined.Hotel, contentDescription = "Reservas", modifier = Modifier.size(17.dp)) },
                    selected = true, onClick = aReservas,
                    shape = RoundedCornerShape(0.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Noticias") },
                    icon = { Icon(Icons.Outlined.Newspaper, contentDescription = "Noticias", modifier = Modifier.size(17.dp)) },
                    selected = false, onClick = aNoticias,
                    shape = RoundedCornerShape(0.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Cerrar Sesión") },
                    icon = { Icon(Icons.Outlined.Logout, contentDescription = "Cerrar Sesión", modifier = Modifier.size(17.dp)) },
                    selected = false, onClick = aLogin,
                    shape = RoundedCornerShape(0.dp)
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    onDrawerClick = { scope.launch { drawerState.open() } },
                    title = "Reservaciones"
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 15.dp)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CalendarToday,
                        contentDescription = "Icono de calendario",
                        modifier = Modifier.size(25.dp),
                        tint = Color(0xFF00A6FF)
                    )
                    Text(
                        text = "Tus reservaciones",
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Revisa y gestiona las reservas que has realizado",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.padding(top = 40.dp))
                } else {
                    // 🔹 POSADAS
                    Text(
                        text = "Posadas",
                        style = MaterialTheme.typography.titleMedium.copy(fontSize = 22.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF7FAFE))
                            .padding(vertical = 6.dp, horizontal = 8.dp)
                    )

                    if (reservas.isEmpty()) {
                        Text(
                            text = "No hay reservas de albergue registradas.",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    } else {
                        reservas.forEach { reserva ->
                            PosadaCard(
                                reserva = reserva,
                                modifier = Modifier,
                                onClick = {
                                    navController.navigate(ScreenNames.ViewReservation.createRoute(reserva.id))
                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // 🔹 TRANSPORTE
                    Text(
                        text = "Transporte",
                        style = MaterialTheme.typography.titleMedium.copy(fontSize = 22.sp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF7FAFE))
                            .padding(vertical = 6.dp, horizontal = 8.dp)
                    )

                    if (transporte.isEmpty()) {
                        Text(
                            text = "No hay reservas de transporte registradas.",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    } else {
                        transporte.forEach { viaje ->
                            TransporteCard(
                                reserva = viaje,
                                modifier = Modifier,
                                onClick = {
                                    navController.navigate(ScreenNames.ViewTransport.createRoute(viaje.id))
                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}
