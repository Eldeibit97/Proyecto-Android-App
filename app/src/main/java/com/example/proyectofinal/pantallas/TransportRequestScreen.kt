package com.example.proyectofinal.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.componentes.TopBar
import com.example.proyectofinal.componentes.TransportCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ReservaTransporteScreen(aHome: () -> Unit = {}, solicitar: () -> Unit = {},
                            aViaje: () -> Unit = {}, aLogin: () -> Unit = {},
                            aReservas: () -> Unit = {}, aNoticias: () -> Unit = {}) {

    val scrollState = rememberScrollState()
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
                    selected = true,
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
            topBar = { TopBar(onDrawerClick = { scope.launch { drawerState.open() } }, title = "Solicitar Transporte") }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .navigationBarsPadding()
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
                // Botones de acción (Enviar / Limpiar)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = aHome,
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
                        onClick = solicitar,
                        modifier = Modifier,
                        enabled = true,
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(
                            text = "Enviar solicitud",
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
