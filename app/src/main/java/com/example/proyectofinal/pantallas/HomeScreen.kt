package com.example.proyectofinal.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.componentes.AlbergueInfoCard
import com.example.proyectofinal.componentes.MapsCard
import com.example.proyectofinal.componentes.TopBar
import com.example.proyectofinal.modelos.Albergue
import com.example.proyectofinal.modelos.getAlbergues
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun HomeScreen(albergues: List<Albergue> = getAlbergues(),
               aTransport: () -> Unit = {}, aReservation: (Albergue) -> Unit = {},
               aHome: () -> Unit = {}, aViaje: () -> Unit = {}, aLogin: () -> Unit = {},
               aReservas: () -> Unit = {}, aNoticias: () -> Unit = {}) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(150.dp)) {
                Text("Opciones", modifier = Modifier.padding(16.dp))
                NavigationDrawerItem(
                    label = { Text("Home") },
                    selected = true,
                    onClick = { aHome() }
                )
                NavigationDrawerItem(
                    label = { Text("Viaje") },
                    selected = false,
                    onClick = { aViaje() }
                )
                NavigationDrawerItem(
                    label = {Text(text = "Reservas")},
                    selected = false,
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
        Scaffold(
            topBar = { TopBar(onDrawerClick = { scope.launch { drawerState.open() } }, title = "Ubicaciones") }
        ) { innerPadding ->
            Column(modifier = Modifier.fillMaxSize().padding(innerPadding)
                .verticalScroll(scrollState)) {
                MapsCard()
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "Ubicaciones disponibles (3)",
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "Selecciona una ubicación para realizar una reserva",
                        modifier = Modifier.padding(5.dp),
                        fontSize = 15.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
                albergues.forEach {  albergue ->
                    AlbergueInfoCard(
                        albergue = albergue,
                        aSolicitarViaje = aTransport,
                        aReservar = aReservation
                    )
                }
            }
        }
    }
}