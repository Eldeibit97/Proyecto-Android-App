package com.example.proyectofinal.pantallas

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Estados locales
    var totalPersonas by remember { mutableIntStateOf(0) }
    var llegada by remember { mutableStateOf<Long?>(null) }
    var salida by remember { mutableStateOf<Long?>(null) }
    var hombres by remember { mutableIntStateOf(0) }
    var mujeres by remember { mutableIntStateOf(0) }
    var cardOriginalVisible by remember { mutableStateOf(true) }

    val persistentCardScale by animateFloatAsState(
        if (cardOriginalVisible) 0f else 1f,
        label = "scale"
    )

    // 🔹 Datos del usuario autenticado
    var nombre by remember { mutableStateOf("Desconocido") }
    var apellido by remember { mutableStateOf("Desconocido") }
    var celular by remember { mutableStateOf("0") }
    var uid by remember { mutableStateOf("sin_usuario") }

    // 🔹 Cargar datos del usuario autenticado desde Firebase
    LaunchedEffect(Unit) {
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        uid = user?.uid ?: "sin_usuario"

        if (uid != "sin_usuario") {
            val db = FirebaseFirestore.getInstance()
            db.collection("users").document(uid).get()
                .addOnSuccessListener { doc ->
                    if (doc.exists()) {
                        nombre = doc.getString("nombre") ?: "Desconocido"
                        apellido = doc.getString("apellido") ?: "Desconocido"
                        celular = doc.getString("telefono") ?: "0"
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(context, "Error al cargar datos del usuario", Toast.LENGTH_SHORT).show()
                }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(150.dp)) {
                Text("Opciones", modifier = Modifier.padding(16.dp))
                NavigationDrawerItem(
                    label = { Text("Home") },
                    icon = { Icon(Icons.Outlined.Home, null, Modifier.size(17.dp)) },
                    selected = false, onClick = { aHome() }
                )
                NavigationDrawerItem(
                    label = { Text("Viaje") },
                    icon = { Icon(Icons.Outlined.CarCrash, null, Modifier.size(17.dp)) },
                    selected = false, onClick = { aViaje() }
                )
                NavigationDrawerItem(
                    label = { Text("Reservas") },
                    icon = { Icon(Icons.Outlined.Hotel, null, Modifier.size(17.dp)) },
                    selected = false, onClick = { aReservas() }
                )
                NavigationDrawerItem(
                    label = { Text("Noticias") },
                    icon = { Icon(Icons.Outlined.Newspaper, null, Modifier.size(17.dp)) },
                    selected = false, onClick = { aNoticias() }
                )
                NavigationDrawerItem(
                    label = { Text("Cerrar Sesión") },
                    icon = { Icon(Icons.Outlined.Logout, null, Modifier.size(17.dp)) },
                    selected = false, onClick = { aLogin() }
                )
            }
        }
    ) {
        Scaffold(
            topBar = { TopBar(onDrawerClick = { scope.launch { drawerState.open() } }, title = "Reserva") }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
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

                    // Datos del usuario (desde Firestore)
                    UsuarioReservationDetailsCard()

                    Spacer(modifier = Modifier.height(12.dp))

                    // Detalles de la reserva
                    ReservaDetailsCard(
                        albergue = albergue,
                        llegada = { llegada = it },
                        salida = { salida = it },
                        total = { totalPersonas = it },
                        hombresCallback = { hombres = it },
                        mujeresCallback = { mujeres = it }
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
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.onTertiary
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
                                    val nombreAlbergue = albergue.nombre
                                    saveReservation(
                                        nombre = nombre,
                                        apellido = apellido,
                                        celular = celular,
                                        albergueNombre = nombreAlbergue,
                                        fechaLlegada = llegada,
                                        fechaSalida = salida,
                                        numPersonas = totalPersonas,
                                        hombres = hombres,
                                        mujeres = mujeres,
                                        uid = uid,
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

                // Tarjeta animada superior
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

// 🔧 Guarda la reserva en Firestore
fun saveReservation(
    nombre: String,
    apellido: String,
    celular: String,
    albergueNombre: String,
    fechaLlegada: Long?,
    fechaSalida: Long?,
    numPersonas: Int,
    hombres: Int,
    mujeres: Int,
    uid: String,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val db = FirebaseUtils.db

    val reserva = hashMapOf(
        "albergue" to albergueNombre,
        "apellido" to apellido,
        "celular" to celular,
        "fechaLlegada" to (fechaLlegada ?: "No especificada"),
        "fechaSalida" to (fechaSalida ?: "No especificada"),
        "hombres" to hombres,
        "mujeres" to mujeres,
        "nombre" to nombre,
        "numPersonas" to numPersonas,
        "timestamp" to System.currentTimeMillis(),
        "uid" to uid
    )

    db.collection("reservations")
        .add(reserva)
        .addOnSuccessListener { onSuccess() }
        .addOnFailureListener { e -> onError(e.message ?: "Error al guardar reserva") }
}
