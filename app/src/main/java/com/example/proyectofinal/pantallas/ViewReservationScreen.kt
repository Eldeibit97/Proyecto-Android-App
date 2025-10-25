package com.example.proyectofinal.pantallas

import androidx.compose.foundation.layout.*
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
import androidx.compose.material.icons.outlined.PeopleAlt
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.componentes.AlbergueReservationDetailsCard
import com.example.proyectofinal.modelos.Albergue
import com.example.proyectofinal.modelos.Reserva
import com.example.proyectofinal.componentes.TopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationConfirmationScreen(
    albergue: Albergue = Albergue(), reserva: Reserva? = Reserva(), onRegresar: () -> Unit = {},
    aViaje: () -> Unit = {}, aHome: () -> Unit = {},
    aLogin: () -> Unit = {}, aReservas: () -> Unit = {}, aNoticias: () -> Unit = {}
) {
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
    ){
        Scaffold(
            topBar = { TopBar(onDrawerClick = { scope.launch { drawerState.open() } }, title = "Detalles de Reserva") }
        ) { innerPadding ->
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AlbergueReservationDetailsCard(albergue = albergue)
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)) {
                            Text("Información personal", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Spacer(Modifier.height(8.dp))

                            ElevatedCard(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Column(Modifier.padding(12.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Outlined.Person,
                                            contentDescription = null,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(Modifier.width(10.dp))
                                        Column {
                                            Text("Nombre", fontSize = 15.sp, color = Color.Gray,fontWeight = FontWeight.SemiBold)
                                            Text(
                                                text = reserva?.nombreResponsable ?: "",
                                                fontSize = 20.sp
                                            )
                                        }
                                    }
                                    HorizontalDivider(Modifier.padding(vertical = 8.dp))

                                    // Fila: Apellido
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Outlined.Person,
                                            contentDescription = null,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(Modifier.width(10.dp))
                                        Column {
                                            Text("Apellido", fontSize = 15.sp, color = Color.Gray, fontWeight = FontWeight.SemiBold)
                                            Text(
                                                text = reserva?.apellidoResponsable ?: "",
                                                fontSize = 20.sp
                                            )
                                        }
                                    }
                                    HorizontalDivider(Modifier.padding(vertical = 8.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Outlined.Phone, // requiere material-icons-extended
                                            contentDescription = null,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(Modifier.width(10.dp))
                                        Column {
                                            Text("Teléfono", fontSize = 15.sp, color = Color.Gray, fontWeight = FontWeight.SemiBold)
                                            Text(
                                                text = reserva?.celular.toString(),
                                                fontSize = 20.sp
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(Modifier.height(8.dp))
                            Text("Fechas", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Spacer(Modifier.height(8.dp))

                            ElevatedCard(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Column(Modifier.padding(12.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Outlined.CalendarToday,
                                            contentDescription = null,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(Modifier.width(10.dp))
                                        Column {
                                            Text("Llegada", fontSize = 15.sp, color = Color.Gray, fontWeight = FontWeight.SemiBold)
                                            Text(
                                                text = reserva?.fechaLlegada ?: "",
                                                fontSize = 20.sp
                                            )
                                        }
                                    }
                                    HorizontalDivider(Modifier.padding(vertical = 8.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Outlined.CalendarToday,
                                            contentDescription = null,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(Modifier.width(10.dp))
                                        Column {
                                            Text("Salida", fontSize = 15.sp, color = Color.Gray, fontWeight = FontWeight.SemiBold)
                                            Text(
                                                text = reserva?.fechaSalida ?: "",
                                                fontSize = 20.sp
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(Modifier.height(10.dp))
                            Text("Cantidad de personas", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Spacer(Modifier.height(8.dp))

                            ElevatedCard(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Column(Modifier.padding(12.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Outlined.PeopleAlt,
                                            contentDescription = null,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(Modifier.width(10.dp))
                                        Column {
                                            Text(
                                                text = reserva?.numPersonas.toString(),
                                                fontSize = 20.sp
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = onRegresar,
                        modifier = Modifier.fillMaxWidth(0.5f),
                        enabled = true,
                        shape = RoundedCornerShape(5.dp)) {
                        Text(text = "Regresar")
                    }
                }
            }
        }
    }
}


/**
@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun PreviewReservationConfirmationScreen() {
    ReservationConfirmationScreen(
        id = 1
    )
}

**/