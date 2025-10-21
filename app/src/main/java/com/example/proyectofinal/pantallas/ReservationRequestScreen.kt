package com.example.proyectofinal.pantallas

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.componentes.*
import com.example.proyectofinal.modelos.Albergue
import com.example.proyectofinal.utils.FirebaseUtils
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ReservationRequestScreen(
    albergue: Albergue? = Albergue(),
    onRegresar: () -> Unit = {},
    onReservar: () -> Unit = {},
    aViaje: () -> Unit = {},
    aHome: () -> Unit = {},
    aLogin: () -> Unit = {},
    aReservas: () -> Unit = {},
    aNoticias: () -> Unit = {}
) {
    // Estados locales
    var totalPersonas by remember { mutableIntStateOf(0) }
    var llegada by remember { mutableStateOf<Long?>(null) }
    var salida by remember { mutableStateOf<Long?>(null) }
    var cardOriginalVisible by remember { mutableStateOf(true) }

    val context = LocalContext.current
    val persistentCardScale by animateFloatAsState(
        if (cardOriginalVisible) 0f else 1f,
        label = "scale"
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Datos del usuario (estos podrían venir de Firebase Auth o un formulario)
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(150.dp)) {
                Text("Opciones", modifier = Modifier.padding(16.dp))
                NavigationDrawerItem(label = { Text("Home") }, selected = false, onClick = { aHome() })
                NavigationDrawerItem(label = { Text("Viaje") }, selected = false, onClick = { aViaje() })
                NavigationDrawerItem(label = { Text("Reservas") }, selected = false, onClick = { aReservas() })
                NavigationDrawerItem(label = { Text("Noticias") }, selected = false, onClick = { aNoticias() })
                NavigationDrawerItem(label = { Text("Cerrar Sesión") }, selected = false, onClick = { aLogin() })
            }
        }
    ) {
        Scaffold(
            topBar = { TopBar(onDrawerClick = { scope.launch { drawerState.open() } }, title = "Reserva") }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Tarjeta del albergue
                    AlbergueReservationDetailsCard(
                        modifier = Modifier.onGloballyPositioned { layoutCoordinates ->
                            cardOriginalVisible = layoutCoordinates.positionInRoot().y >= -125
                        },
                        albergue = albergue,
                        expand = cardOriginalVisible
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Datos del usuario (aquí podrías conectar con datos de usuario autenticado)
                    UsuarioReservationDetailsCard()

                    Spacer(modifier = Modifier.height(12.dp))

                    // Detalles de reserva
                    ReservaDetailsCard(
                        albergue = albergue,
                        llegada = { llegada = it },
                        salida = { salida = it },
                        total = { totalPersonas = it }
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    PrecioServicioCard(albergue = albergue)
                    Spacer(modifier = Modifier.padding(8.dp))

                    // Botones de acción
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Botón cancelar
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

                        // Botón confirmar reserva
                        Button(
                            onClick = {
                                if (albergue != null) {
                                    val nombreAlbergue = albergue.nombre ?: "Sin nombre"
                                    val fechaLlegada = llegada?.toString() ?: "No especificada"
                                    val fechaSalida = salida?.toString() ?: "No especificada"

                                    saveReservation(
                                        nombre = nombre,
                                        apellido = apellido,
                                        celular = celular,
                                        albergueNombre = nombreAlbergue,
                                        fechaLlegada = fechaLlegada,
                                        fechaSalida = fechaSalida,
                                        numPersonas = totalPersonas,
                                        onSuccess = {
                                            Toast.makeText(context, "Reserva guardada ✅", Toast.LENGTH_SHORT).show()
                                            onReservar()
                                        },
                                        onError = { msg ->
                                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                                        }
                                    )
                                } else {
                                    Toast.makeText(context, "Error: No se encontró el albergue", Toast.LENGTH_LONG).show()
                                }
                            },
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

                // Tarjeta fija superior
                AlbergueReservationDetailsCard(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .graphicsLayer {
                            scaleX = persistentCardScale
                            scaleY = persistentCardScale
                        },
                    albergue = albergue,
                    expand = cardOriginalVisible
                )
            }
        }
    }
}

// 🔧 FUNCIÓN: Guarda la reserva en Firestore
fun saveReservation(
    nombre: String,
    apellido: String,
    celular: String,
    albergueNombre: String,
    fechaLlegada: String,
    fechaSalida: String,
    numPersonas: Int,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val db = FirebaseUtils.db
    val reserva = hashMapOf(
        "nombre" to nombre,
        "apellido" to apellido,
        "celular" to celular,
        "albergue" to albergueNombre,
        "fechaLlegada" to fechaLlegada,
        "fechaSalida" to fechaSalida,
        "numPersonas" to numPersonas,
        "timestamp" to System.currentTimeMillis()
    )

    db.collection("reservations")
        .add(reserva)
        .addOnSuccessListener { onSuccess() }
        .addOnFailureListener { e -> onError(e.message ?: "Error al guardar reserva") }
}
