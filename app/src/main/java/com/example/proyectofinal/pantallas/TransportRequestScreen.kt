package com.example.proyectofinal.pantallas

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CarCrash
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Hotel
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun ReservaTransporteScreen(aHome: () -> Unit = {}, solicitar: (Int) -> Unit = {},
                            aViaje: () -> Unit = {}, aLogin: () -> Unit = {},
                            aReservas: () -> Unit = {}, aNoticias: () -> Unit = {}) {
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
                    selected = true, onClick = { aViaje() },
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
            topBar = { TopBar(onDrawerClick = { scope.launch { drawerState.open() } }, title = "Solicitar Transporte") }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Precio del servicio: $20",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = " Completa el formulario para solicitar transporte",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 8.dp))
                Spacer(modifier = Modifier.height(8.dp))
                TransportCard()
                Spacer(modifier = Modifier.height(8.dp))

                // 🔹 Botones
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = aHome,
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonColors(MaterialTheme.colorScheme.onTertiary,
                            MaterialTheme.colorScheme.onBackground,
                            MaterialTheme.colorScheme.onTertiary,
                            MaterialTheme.colorScheme.onBackground)
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
                                onSuccess = { createdId ->
                                    Toast.makeText(context, "Solicitud enviada ✅", Toast.LENGTH_SHORT).show()
                                    solicitar(createdId)
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
    onSuccess: (Int) -> Unit,
    onError: (String) -> Unit
) {
    val createdId = (System.currentTimeMillis() % Int.MAX_VALUE).toInt()
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
        .addOnSuccessListener { onSuccess(createdId) }
        .addOnFailureListener { e -> onError(e.message ?: "Error al enviar solicitud") }
}