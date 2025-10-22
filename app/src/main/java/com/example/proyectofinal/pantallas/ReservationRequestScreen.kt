package com.example.proyectofinal.pantallas

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.componentes.AlbergueReservationDetailsCard
import com.example.proyectofinal.componentes.PrecioServicioCard
import com.example.proyectofinal.componentes.ReservaDetailsCard
import com.example.proyectofinal.componentes.TopBar
import com.example.proyectofinal.componentes.UsuarioReservationDetailsCard
import com.example.proyectofinal.modelos.Albergue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ReservationRequestScreen(
    albergue: Albergue? = Albergue(), onRegresar: () -> Unit = {},
    onReservar: () -> Unit = {}, aViaje: () -> Unit = {},
    aHome: () -> Unit = {}, aLogin: () -> Unit = {},
    aReservas: () -> Unit = {}, aNoticias: () -> Unit = {}
) {
    var totalPersonas by remember { mutableIntStateOf(0) }
    var llegada by remember { mutableStateOf<Long?>(null) }
    var salida by remember { mutableStateOf<Long?>(null) }
    var cardOriginalVisible by remember { mutableStateOf(true) }

    val persistentCardScale by animateFloatAsState(
        if (cardOriginalVisible) 0f else 1f,
        label = "scale"
    )
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
            topBar = { TopBar(onDrawerClick = { scope.launch { drawerState.open() } }, title = "Reserva") }
        ) { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding).fillMaxSize()
            ) {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AlbergueReservationDetailsCard(modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                        cardOriginalVisible = layoutCoordinates.positionInRoot().y >= -125
                    }, albergue = albergue, expand = cardOriginalVisible)
                    Spacer(modifier = Modifier.height(12.dp))
                    UsuarioReservationDetailsCard()
                    Spacer(modifier = Modifier.height(12.dp))
                    ReservaDetailsCard(
                        albergue = albergue,
                        llegada = { llegada = it },
                        salida = { salida = it },
                        total = { totalPersonas = it })
                    Spacer(modifier = Modifier.height(12.dp))
                    PrecioServicioCard(albergue = albergue)
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
                            colors = ButtonColors(MaterialTheme.colorScheme.onTertiary,
                                MaterialTheme.colorScheme.onBackground,
                                MaterialTheme.colorScheme.onTertiary,
                                MaterialTheme.colorScheme.onBackground)
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
                AlbergueReservationDetailsCard(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .graphicsLayer {
                            scaleX = persistentCardScale
                            scaleY = persistentCardScale
                        },
                    albergue = albergue, expand = cardOriginalVisible
                )
            }
        }
    }
}