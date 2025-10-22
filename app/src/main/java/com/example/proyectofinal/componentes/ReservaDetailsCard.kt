package com.example.proyectofinal.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.modelos.Albergue
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ReservaDetailsCard(
    albergue: Albergue? = Albergue(),
    llegada: (Long?) -> Unit = {},
    salida: (Long?) -> Unit = {},
    total: (Int) -> Unit = {},
    hombresCallback: (Int) -> Unit = {},    // 🔹 Nuevo callback
    mujeresCallback: (Int) -> Unit = {}     // 🔹 Nuevo callback
) {
    var personasHombres by rememberSaveable { mutableIntStateOf(0) }
    var personasMujeres by rememberSaveable { mutableIntStateOf(0) }
    var fechaLlegada by remember { mutableStateOf<Long?>(null) }
    var fechaSalida by remember { mutableStateOf<Long?>(null) }
    var tipoSalida by remember { mutableStateOf(false) }
    var tipoReserva by remember { mutableStateOf(true) }
    var showDatePickerLlegada by remember { mutableStateOf(false) }
    var showDatePickerSalida by remember { mutableStateOf(false) }

    val datePickerStateLlegada = rememberDatePickerState()
    val datePickerStateSalida = rememberDatePickerState()

    val validarTotalPersonas by remember {
        derivedStateOf {
            when (tipoReserva) {
                false -> ValidarTotal(albergue = albergue, hombres = personasHombres, mujeres = personasMujeres)
                else -> false
            }
        }
    }

    if (tipoReserva) {
        // Si es individual
        total(1)
    } else {
        // Si es grupal
        total(personasHombres + personasMujeres)
    }


    // 🔹 Cada vez que cambian, avisamos al componente padre
    hombresCallback(personasHombres)
    mujeresCallback(personasMujeres)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            Column(modifier = Modifier.padding(vertical = 10.dp)) {
                Text(
                    text = "Selecciona las fechas para reservar",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // --- Date pickers ---
                if (showDatePickerLlegada) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePickerLlegada = false },
                        confirmButton = {
                            Button(onClick = {
                                val selectDate = datePickerStateLlegada.selectedDateMillis
                                if (selectDate != null) {
                                    fechaLlegada = selectDate
                                    llegada(selectDate)
                                }
                                showDatePickerLlegada = false
                            }) {
                                Text(text = "confirmar")
                            }
                        },
                        dismissButton = {
                            Button(onClick = { showDatePickerLlegada = false }) {
                                Text(text = "cancelar")
                            }
                        }
                    ) { DatePicker(state = datePickerStateLlegada) }
                }

                if (showDatePickerSalida) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePickerSalida = false },
                        confirmButton = {
                            Button(onClick = {
                                val selectDate = datePickerStateSalida.selectedDateMillis
                                if (selectDate != null) {
                                    fechaSalida = selectDate
                                    salida(selectDate)
                                }
                                showDatePickerSalida = false
                            }) {
                                Text(text = "confirmar")
                            }
                        },
                        dismissButton = {
                            Button(onClick = { showDatePickerSalida = false }) {
                                Text(text = "cancelar")
                            }
                        }
                    ) { DatePicker(state = datePickerStateSalida) }
                }

                OutlinedTextField(
                    value = fechaLlegada?.let { formatDateString(it) } ?: "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .pointerInput(fechaLlegada) {
                            awaitEachGesture {
                                awaitFirstDown(pass = PointerEventPass.Initial)
                                val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                                if (upEvent != null) showDatePickerLlegada = true
                            }
                        },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.DateRange,
                            contentDescription = "Fecha llegada",
                            modifier = Modifier.size(17.dp)
                        )
                    },
                    label = { Text(text = "Fecha de llegada") },
                    placeholder = { Text(text = "DD/MM/AAAA", fontSize = 15.sp) },
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (!tipoSalida) {
                    OutlinedTextField(
                        value = fechaSalida?.let { formatDateString(it) } ?: "",
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .pointerInput(fechaSalida) {
                                awaitEachGesture {
                                    awaitFirstDown(pass = PointerEventPass.Initial)
                                    val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                                    if (upEvent != null) showDatePickerSalida = true
                                }
                            },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.CalendarMonth,
                                contentDescription = "Fecha salida",
                                modifier = Modifier.size(17.dp)
                            )
                        },
                        label = { Text(text = "Fecha de salida") },
                        placeholder = { Text(text = "DD/MM/AAAA", fontSize = 15.sp) },
                        shape = RoundedCornerShape(10.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Salida indefinida", modifier = Modifier.padding(horizontal = 6.dp), fontSize = 14.sp)
                    Switch(checked = tipoSalida, onCheckedChange = { tipoSalida = it })
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Tipo de Reserva",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp).clickable(onClick = { tipoReserva = true }),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = tipoReserva, onClick = null)
                        Text(text = "Individual", modifier = Modifier.padding(horizontal = 6.dp), fontSize = 14.sp)
                    }
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp).clickable(onClick = { tipoReserva = false }),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = !tipoReserva, onClick = null)
                        Text(text = "Grupal", modifier = Modifier.padding(horizontal = 6.dp), fontSize = 14.sp)
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                if (!tipoReserva) {
                    Text(text = "Cantidad de personas", fontWeight = FontWeight.Bold, fontSize = 14.sp)

                    if (validarTotalPersonas) {
                        Text(
                            text = "El total de personas excede la capacidad disponible del albergue.",
                            modifier = Modifier.padding(6.dp),
                            fontSize = 15.sp,
                            color = Color.Red
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = "Hombres", fontSize = 14.sp)
                    OutlinedTextField(
                        value = if (personasHombres == 0) "" else personasHombres.toString(),
                        onValueChange = {
                            personasHombres = it.toIntOrNull() ?: 0
                            hombresCallback(personasHombres)
                        },
                        isError = validarTotalPersonas,
                        leadingIcon = {
                            Icon(imageVector = Icons.Outlined.Person, contentDescription = "Personas", modifier = Modifier.size(17.dp))
                        },
                        label = { Text(text = "¿Cuántos hombres?") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(text = "Mujeres", fontSize = 14.sp)
                    OutlinedTextField(
                        value = if (personasMujeres == 0) "" else personasMujeres.toString(),
                        onValueChange = {
                            personasMujeres = it.toIntOrNull() ?: 0
                            mujeresCallback(personasMujeres)
                        },
                        isError = validarTotalPersonas,
                        leadingIcon = {
                            Icon(imageVector = Icons.Outlined.Person, contentDescription = "Personas", modifier = Modifier.size(17.dp))
                        },
                        label = { Text(text = "¿Cuántas mujeres?") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.padding(6.dp))
                Text(
                    text = "Las fechas de reserva pueden ajustarse según disponibilidad del albergue.",
                    modifier = Modifier.padding(horizontal = 12.dp),
                    textAlign = TextAlign.Justify,
                    fontSize = 13.sp
                )
            }
        }
    }
}


private fun ValidarTotal(albergue: Albergue? = Albergue(),hombres: Int, mujeres: Int) : Boolean{
    if(hombres < 0 && mujeres < 0) return false
    val total = hombres + mujeres
    return total !in 0..((albergue?.capacidad ?: 60)-(albergue?.disponibilidad ?: 0))
}

private fun formatDateString(millis: Long): String {
    val date = Date(millis)
    val format = SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    return format.format(date)
}