package com.example.proyectofinal.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.proyectofinal.modelos.getNoticias
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun NewsScreen(aHome: () -> Unit = {}, aViaje: () -> Unit = {}, aLogin: () -> Unit = {},
                        aReservas: () -> Unit = {}, aNoticias: () -> Unit = {}){
    val noticias = getNoticias()
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
                    label = {Text(text = "Reservas")},
                    selected = false,
                    onClick = { aReservas() }
                )
                NavigationDrawerItem(
                    label = { Text("Noticias") },
                    selected = true,
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
        Scaffold(topBar = { TopBar(onDrawerClick = { scope.launch { drawerState.open() } }, title= "Notificaciones") }
        ) { innerPadding ->
            Column(modifier = Modifier.fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 15.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Outlined.Notifications,
                            contentDescription = "Icono de notificaciones",
                            modifier = Modifier.size(25.dp),
                            tint = Color(0xFFFF9800))
                        Text(text = "Noticias y Avisos",
                            modifier = Modifier.padding(horizontal = 8.dp),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.padding(6.dp))
                    Text(text = "Mantente informado de las ultimas noticias y avisos de nuestros albergues",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Justify)
                    Spacer(modifier = Modifier.padding(6.dp))
                }
                LazyColumn() {
                    items(items = noticias) { noticia ->
                        NoticiaDisplayCard(noticia = noticia)
                    }
                }
            }
        }
    }
}