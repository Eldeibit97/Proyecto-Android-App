package com.example.proyectofinal.pantallas

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.componentes.TopBar
import com.example.proyectofinal.componentes.TransportCard
import com.example.proyectofinal.utils.FirebaseUtils
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ReservaTransporteScreen(
    aHome: () -> Unit = {},
    avanzar: () -> Unit = {},
    aViaje: () -> Unit = {},
    aLogin: () -> Unit = {},
    aReservas: () -> Unit = {},
    aNoticias: () -> Unit = {}
) {
    val context = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // 🔹 Estados del formulario
    var origen by remember { mutableStateOf("") }
    var destino by remember { mutableStateOf("") }
    var momentoInicio by remember { mutableStateOf("Ahora") }
    var personas by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(150.dp)) {
                Text("Opciones", modifier = Modifier.padding(16.dp))
                NavigationDrawerItem(label = { Text("Home") }, selected = false, onClick = { aHome() })
                NavigationDrawerItem(label = { Text("Viaje") }, selected = true, onClick = { aViaje() })
                NavigationDrawerItem(label = { Text("Reservas") }, selected = false, onClick = { aReservas() })
                NavigationDrawerItem(label = { Text("Noticias") }, selected = false, onClick = { aNoticias() })
                NavigationDrawerItem(label = { Text("Cerrar Sesión") }, selected = false, onClick = { aLogin() })
            }
        }
    ) {
        Scaffold(
            topBar = { TopBar(onDrawerClick = { scope.launch { drawerState.open() } }, title = "Solicitar Transporte") }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "*Servicio gratuito de Cáritas - Completa el formulario para solicitar transporte",
                    fontSize = 11.sp,
                    modifier = Modifier.padding(horizontal = 8.dp).align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(6.dp))

                TransportCard(
                    origenCallback = { origen = it },
                    destinoCallback = { destino = it },
                    momentoCallback = { momentoInicio = it },
                    personasCallback = { personas = it },
                    notasCallback = { notas = it }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // 🔹 Botones
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = aHome,
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color(0xFF03A9F4)
                        )
                    ) {
                        Text("Cancelar y regresar", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }

                    Button(
                        onClick = {
                            saveTransportRequest(
                                origen = origen,
                                destino = destino,
                                momentoInicio = momentoInicio,
                                personas = personas,
                                notas = notas,
                                uid = "usuario_no_autenticado",
                                onSuccess = {
                                    Toast.makeText(context, "Solicitud enviada ✅", Toast.LENGTH_SHORT).show()
                                    avanzar()
                                },
                                onError = { msg ->
                                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                                }
                            )
                        },
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text("Enviar solicitud", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

// 🔧 Guardar en Firestore
fun saveTransportRequest(
    origen: String,
    destino: String,
    momentoInicio: String,
    personas: String,
    notas: String,
    uid: String,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val db = FirebaseUtils.db

    val solicitud = hashMapOf(
        "origen" to origen,
        "destino" to destino,
        "momentoInicio" to momentoInicio,
        "personas" to personas,
        "notas" to notas,
        "timestamp" to System.currentTimeMillis(),
        "uid" to uid
    )

    db.collection("transporte")
        .add(solicitud)
        .addOnSuccessListener { onSuccess() }
        .addOnFailureListener { e -> onError(e.message ?: "Error al enviar solicitud") }
}
