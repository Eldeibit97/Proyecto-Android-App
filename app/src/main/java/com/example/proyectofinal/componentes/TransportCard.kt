package com.example.proyectofinal.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Notes
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectofinal.R
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun TransportCard(
    origenCallback: (String) -> Unit = {},
    destinoCallback: (String) -> Unit = {},
    momentoCallback: (String) -> Unit = {},
    personasCallback: (String) -> Unit = {},
    notasCallback: (String) -> Unit = {}
) {
    var origen by rememberSaveable { mutableStateOf("") }
    var destino by rememberSaveable { mutableStateOf("") }
    var selHora by rememberSaveable { mutableStateOf("") }
    var hora by rememberSaveable { mutableStateOf("") }
    var personas by rememberSaveable { mutableStateOf("") }
    var notas by rememberSaveable { mutableStateOf("") }
    var expandedPersonas by rememberSaveable { mutableStateOf(false) }

    val opcionesPersonas = listOf(
        "1 persona", "2 personas", "3 personas", "4 personas", "5 o más"
    )
    val timePickerState = rememberTimePickerState(is24Hour = true)
    var showTimePicker by remember { mutableStateOf(false) }

    // 🔹 Tarjeta principal
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        // 🔸 Imagen de encabezado
        Image(
            painter = painterResource(id = R.drawable.tepesa),
            contentDescription = "Foto del taxi",
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // ORIGEN
            Text("Punto de origen", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            OutlinedTextField(
                value = origen,
                onValueChange = { origen = it ; origenCallback(origen)},
                leadingIcon = {
                    Icon(Icons.Outlined.LocationOn, contentDescription = "Ubicación", modifier = Modifier.size(17.dp))
                },
                placeholder = { Text("Ej: Carretera Nacional", fontSize = 15.sp) },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // DESTINO
            Text("Destino", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            OutlinedTextField(
                value = destino,
                onValueChange = { destino = it ; destinoCallback(destino)},
                leadingIcon = {
                    Icon(Icons.Outlined.LocationOn, contentDescription = "Ubicación", modifier = Modifier.size(17.dp))
                },
                placeholder = { Text("Ej: Posada del Peregrino", fontSize = 15.sp) },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // MOMENTO DEL VIAJE
            Text("Inicio del viaje", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { selHora = "Ahora" }
                ) {
                    RadioButton(selected = selHora == "Ahora",
                        onClick = {
                            selHora = "Ahora"
                            // Create a formatter and use it to format the current Date
                            val time = System.currentTimeMillis()
                            hora = formatTimeString(time)
                            momentoCallback(hora)
                        })
                    Text("Ahora", modifier = Modifier.padding(start = 4.dp))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { selHora = "Más tarde" }
                ) {
                    RadioButton(selected = selHora == "Más tarde", onClick = { selHora = "Más tarde" })
                    Text("Más tarde", modifier = Modifier.padding(start = 4.dp))
                }
            }
            if (showTimePicker) {
                TimePickerDialog(
                    onDismissRequest = { showTimePicker = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                val formattedTime = String.format("%02d:%02d", timePickerState.hour, timePickerState.minute)
                                hora = formattedTime
                                momentoCallback(hora)
                                showTimePicker = false
                            }
                        ) { Text("Aceptar") }
                    },
                    title = { Text(text = "Selecciona la hora") },
                    dismissButton = {
                        TextButton(onClick = { showTimePicker = false }) { Text("Cancelar") }
                    }
                ) {
                    TimePicker(state = timePickerState)
                }
            }
            if(selHora == "Más tarde") {
                OutlinedTextField(
                    value = hora,
                    onValueChange = { hora = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .pointerInput(hora) {
                            awaitEachGesture {
                                awaitFirstDown(pass = PointerEventPass.Initial)
                                val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                                if (upEvent != null) showTimePicker = true
                            }
                        },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.DateRange,
                            contentDescription = "Hora de salida",
                            modifier = Modifier.size(17.dp)
                        )
                    },
                    readOnly = true,
                    label = { Text(text = "Hora de salida") },
                    placeholder = { Text(text = "", fontSize = 15.sp) },
                    shape = RoundedCornerShape(10.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            // CANTIDAD DE PERSONAS
            Text("Cantidad de personas", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            ExposedDropdownMenuBox(
                expanded = expandedPersonas,
                onExpandedChange = { expandedPersonas = !expandedPersonas },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = personas.ifBlank { "¿Cuántas personas?" },
                    onValueChange = {},
                    readOnly = true,
                    leadingIcon = {
                        Icon(Icons.Outlined.Person, contentDescription = "Personas", modifier = Modifier.size(17.dp))
                    },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedPersonas) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
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
                                personasCallback(personas)
                                expandedPersonas = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // NOTAS
            Text("Notas Adicionales (opcional)", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            OutlinedTextField(
                value = notas,
                onValueChange = { notas = it ; notasCallback(notas)},
                leadingIcon = {
                    Icon(Icons.Outlined.Notes, contentDescription = "Notas", modifier = Modifier.size(17.dp))
                },
                placeholder = { Text("Ej: Necesito silla de ruedas", fontSize = 15.sp) },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

private fun formatTimeString(millis: Long): String{
    val date = Date(millis)
    val format = SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
    return format.format(date)
}