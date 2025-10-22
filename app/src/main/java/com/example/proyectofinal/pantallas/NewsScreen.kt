package com.example.proyectofinal.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.componentes.NoticiaDisplayCard
import com.example.proyectofinal.componentes.TopBar
import com.example.proyectofinal.modelos.Noticia
import com.example.proyectofinal.modelos.fetchNoticias
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun NewsScreen(
    aHome: () -> Unit = {},
    aViaje: () -> Unit = {},
    aLogin: () -> Unit = {},
    aReservas: () -> Unit = {},
    aNoticias: () -> Unit = {}
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var noticias by remember { mutableStateOf<List<Noticia>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    // 🔹 Cargar las noticias desde Firestore
    LaunchedEffect(Unit) {
        noticias = fetchNoticias()
        isLoading = false
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(150.dp)) {
                Text("Opciones", modifier = Modifier.padding(16.dp))
                NavigationDrawerItem(label = { Text("Home") }, selected = false, onClick = { aHome() })
                NavigationDrawerItem(label = { Text("Viaje") }, selected = false, onClick = { aViaje() })
                NavigationDrawerItem(label = { Text("Reservas") }, selected = false, onClick = { aReservas() })
                NavigationDrawerItem(label = { Text("Noticias") }, selected = true, onClick = { aNoticias() })
                NavigationDrawerItem(label = { Text("Cerrar Sesión") }, selected = false, onClick = { aLogin() })
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopBar(onDrawerClick = { scope.launch { drawerState.open() } }, title = "Noticias y Avisos")
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 15.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Icono de notificaciones",
                        modifier = Modifier.size(25.dp),
                        tint = Color(0xFFFF9800)
                    )
                    Text(
                        text = "Noticias y avisos",
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Mantente informado de las últimas noticias y avisos de nuestros albergues.",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(12.dp))

                when {
                    isLoading -> {
                        CircularProgressIndicator(modifier = Modifier.padding(top = 40.dp))
                    }
                    noticias.isEmpty() -> {
                        Text(
                            text = "No hay noticias disponibles en este momento.",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 40.dp)
                        )
                    }
                    else -> {
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(noticias) { noticia ->
                                NoticiaDisplayCard(noticia = noticia)
                            }
                        }
                    }
                }
            }
        }
    }
}
