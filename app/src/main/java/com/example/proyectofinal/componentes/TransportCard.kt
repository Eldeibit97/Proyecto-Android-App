package com.example.proyectofinal.componentes

import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Notes
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable

fun TransportCard(avanzar: () -> Unit = {}){
    var nombre by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }
    var origen by rememberSaveable { mutableStateOf("") }
    var destino by rememberSaveable { mutableStateOf("") }
    var notas by rememberSaveable { mutableStateOf("") }
    var selHora by rememberSaveable { mutableStateOf("") }
    var personas by rememberSaveable { mutableStateOf("") }
    var expandedPersonas by rememberSaveable { mutableStateOf(false) }

    val opcionesPersonas = listOf(
        "1 persona",
        "2 personas",
        "3 personas",
        "4 personas",
        "5 o más"
    )
    // Card que contiene el formulario
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 4.dp),
        shape = MaterialTheme.shapes.medium,

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(14.dp),

            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Información Personal",
                fontSize = 18.sp)
            Spacer(modifier = Modifier.padding(8.dp))
            Column (modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center) {
                Text(
                    text = "Nombre Completo",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Nombre",
                            modifier = Modifier.size(17.dp)
                        )
                    },
                    placeholder = { Text(text = "Juan Pérez", fontSize = 15.sp) },
                    shape = RoundedCornerShape(10.dp)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Número de telefono",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Phone,
                            contentDescription = "Celular",
                            modifier = Modifier.size(17.dp)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "+52 555 555 5555", fontSize = 15.sp) },
                    shape = RoundedCornerShape(10.dp)
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))

            Text(text = "Detalles del viaje",
                fontSize = 18.sp)

            Spacer(modifier = Modifier.padding(8.dp))
            Column (modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth(),

                verticalArrangement = Arrangement.Center) {

                Text(text = "Punto de origen",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp)
                OutlinedTextField(value = "",
                    onValueChange = {},
                    leadingIcon = {Icon(imageVector = Icons.Outlined.LocationOn,
                        contentDescription = "Ubicacion",
                        modifier = Modifier.size(17.dp))},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {Text(text="Ej: Av. Constitución 123, Centro...", fontSize = 15.sp)},
                    shape = RoundedCornerShape(10.dp))

                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "Destino",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp)
                OutlinedTextField(value = "",
                    onValueChange = {},
                    leadingIcon = {Icon(imageVector = Icons.Outlined.LocationOn,
                        contentDescription = "Ubicacion",
                        modifier = Modifier.size(17.dp))},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {Text(text="Ej: Posada del Peregrino, Simón..", fontSize = 15.sp)},
                    shape = RoundedCornerShape(10.dp))

                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "Inicio del viaje",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp)
                Row(modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Row(modifier = Modifier.padding(4.dp)
                        .clickable(onClick = {selHora = "Ahora"}),
                        verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = when(selHora) {
                            "Ahora" -> true
                            else -> false
                        }, onClick = null)
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Ahora")
                    }
                    Row(modifier = Modifier.padding(4.dp)
                        .clickable(onClick = {selHora = "Más Tarde"}),
                        verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = when(selHora) {
                            "Más Tarde" -> true
                            else -> false
                        }, onClick = null)
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Más Tarde")
                    }
                }
                Text(
                    text = "Cantidad de personas",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                ExposedDropdownMenuBox(
                    expanded = expandedPersonas,
                    onExpandedChange = { expandedPersonas = !expandedPersonas },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = if (personas.isBlank()) "¿Cuántas personas?" else personas,
                        onValueChange = { },
                        readOnly = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Person,
                                contentDescription = "Personas",
                                modifier = Modifier.size(17.dp)
                            )
                        },
                        placeholder = { Text(text = "¿Cuántas personas?", fontSize = 15.sp) },
                        shape = RoundedCornerShape(10.dp),
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedPersonas) },
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expandedPersonas,
                        onDismissRequest = { expandedPersonas = false }
                    ) {
                        opcionesPersonas.forEach { opcion ->
                            DropdownMenuItem(
                                text = { Text(opcion) },
                                onClick = {
                                    personas = opcion
                                    expandedPersonas = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "Notas Adicionales (Opcional)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp)
                OutlinedTextField(value = "",
                    onValueChange = {},
                    leadingIcon = {Icon(imageVector = Icons.Outlined.Notes,
                        contentDescription = "Notas",
                        modifier = Modifier.size(17.dp))},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {Text(text="Notas", fontSize = 15.sp)},
                    shape = RoundedCornerShape(10.dp))

                Spacer(modifier = Modifier.height(6.dp))

                // Botones de acción (Enviar / Limpiar)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = {
                        // limpiar campos
                        nombre = ""
                        telefono = ""
                        origen = ""
                        destino = ""
                        notas = ""
                    }) {
                        Text(text = "Limpiar")
                    }

                    Button(
                        onClick = {
                            // Aquí va la lógica para enviar la solicitud:
                            // validar campos y llamar a tu backend / ViewModel
                            // por ahora llamamos al callback avanzar para integrarlo en la navegación
                            avanzar()
                        },
                        modifier = Modifier,
                        enabled = true,
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonColors(
                            containerColor = Color(0xFF3CB93A),
                            contentColor = Color(0xFFFFFFFF),
                            disabledContainerColor = Color(0xFF9A9A9A),
                            disabledContentColor = Color(0xFFFFFFFF)
                        )

                    ) {
                        Text(text = "Enviar Solicitud")
                    }

                }
            }
        }
    }
}