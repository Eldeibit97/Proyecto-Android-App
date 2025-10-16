package com.example.proyectofinal.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.componentes.AlbergueReservationDetailsCard
import com.example.proyectofinal.componentes.ReservaDetailsCard
import com.example.proyectofinal.modelos.Albergue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ReservationRequestScreen(
    albergue: Albergue? = Albergue(), onRegresar: () -> Unit = {},
    onReservar: () -> Unit = {}, aViaje: () -> Unit = {},
    aHome: () -> Unit = {}, aLogin: () -> Unit = {},
    aReservas: () -> Unit = {}
) {
    var totalPersonas by remember { mutableIntStateOf(0) }
    var llegada by remember { mutableStateOf<Long?>(null) }
    var salida by remember { mutableStateOf<Long?>(null) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
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
                 label = { Text(text = "Reservas") },
                 selected = false,
                 onClick = { aReservas() }
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
            topBar = { TopBar(onDrawerClick = { scope.launch { drawerState.open() } }) }
        ) { innerPadding ->
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AlbergueReservationDetailsCard(albergue)
                Spacer(modifier = Modifier.height(12.dp))
                ReservaDetailsCard(albergue = albergue,llegada = {llegada = it}, salida = {salida = it},
                    total = {totalPersonas = it})
                Spacer(modifier = Modifier.padding(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = onRegresar,
                        modifier = Modifier,
                        enabled = true,
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFFFFF),
                            contentColor = Color(0xFF03A9F4),
                            disabledContainerColor = Color(0xFF9A9A9A),
                            disabledContentColor = Color(0xFFFFFFFF)
                        )
                    ) {
                        Text(
                            text = "Cancelar y regresar",
                            modifier = Modifier.padding(vertical = 3.dp, horizontal = 5.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                    Button(
                        onClick = onReservar,
                        modifier = Modifier,
                        enabled = true,
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(
                            text = "Realizar reserva",
                            modifier = Modifier.padding(vertical = 3.dp, horizontal = 5.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onDrawerClick: () -> Unit,
           title: String = "Reserva"){
    TopAppBar(
        title = {Text(title)},
        navigationIcon = {
            IconButton(onClick = onDrawerClick) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
        }
    )
}