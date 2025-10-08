package com.example.proyectofinal.componentes

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ReservaDetailsCard(llegada: (Long?) -> Unit = {}, salida: (Long?) -> Unit = {}, hombres: (String) -> Unit = {}, mujeres: (String) -> Unit = {}){
    var personasHombres by rememberSaveable { mutableStateOf("") }
    var personasMujeres by rememberSaveable { mutableStateOf("") }
    var fechaLlegada by remember { mutableStateOf<Long?>(null) }
    var fechaSalida by remember { mutableStateOf<Long?>(null) }
    var showDatePickerLlegada by remember { mutableStateOf(false) }
    var showDatePickerSalida by remember { mutableStateOf(false) }
    val datePickerStateLlegada = rememberDatePickerState()
    val datePickerStateSalida = rememberDatePickerState()
    val personasTotal by remember { derivedStateOf { ValidarTotal(personasHombres.toIntOrNull(), personasMujeres.toIntOrNull()) }}
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)) {
            Column(modifier = Modifier.padding(vertical = 10.dp)) {
                // Fechas
                Text(
                    text = "Selecciona las fechas para reservar",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                if(showDatePickerLlegada) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePickerLlegada = false },
                        confirmButton = {
                            Button(onClick = {
                                val selectDate = datePickerStateLlegada.selectedDateMillis
                                if(selectDate != null) {
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
                        }) {
                        DatePicker(state = datePickerStateLlegada)
                    }
                }

                if(showDatePickerSalida) {
                    DatePickerDialog(
                        onDismissRequest = { showDatePickerSalida = false },
                        confirmButton = {
                            Button(onClick = {
                                val selectDate = datePickerStateSalida.selectedDateMillis
                                if(selectDate != null) {
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
                        }) {
                        DatePicker(state = datePickerStateSalida)
                    }
                }

                OutlinedTextField(
                    value = fechaLlegada?.let {formatDateString(it)} ?: "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .pointerInput(fechaLlegada) {
                            awaitEachGesture {
                                awaitFirstDown(pass = PointerEventPass.Initial)
                                val upEvent =
                                    waitForUpOrCancellation(pass = PointerEventPass.Initial)
                                if (upEvent != null) {
                                    showDatePickerLlegada = true
                                }
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
                OutlinedTextField(
                    value = fechaSalida?.let {formatDateString(it)} ?: "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .pointerInput(fechaSalida) {
                            awaitEachGesture {
                                awaitFirstDown(pass = PointerEventPass.Initial)
                                val upEvent =
                                    waitForUpOrCancellation(pass = PointerEventPass.Initial)
                                if (upEvent != null) {
                                    showDatePickerSalida = true
                                }
                            }
                        },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.CalendarMonth,
                            contentDescription = "Fecha salida",
                            modifier = Modifier.size(17.dp)
                        )
                    },
                    label = { Text(text = "Fecha de salida")},
                    placeholder = { Text(text = "DD/MM/AAAA", fontSize = 15.sp) },
                    shape = RoundedCornerShape(10.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Cantidad de personas",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                if(personasTotal){
                    Text(text = "El total de personas por reserva es de maximo 10",
                        modifier = Modifier.padding(6.dp),
                        fontSize = 16.sp,
                        color = Color.Red)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Hombres",
                    fontSize = 14.sp
                )
                OutlinedTextField(
                    value = personasHombres,
                    isError = personasTotal,
                    onValueChange = { personasHombres = it; hombres(it)},
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Personas",
                            modifier = Modifier.size(17.dp)
                        )
                    },
                    label = { Text(text = "¿Cuántos hombres?") },
                    placeholder = { Text(text = "", fontSize = 15.sp) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                )


                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Mujeres",
                    fontSize = 14.sp
                )
                OutlinedTextField(
                    value = personasMujeres,
                    onValueChange = { personasMujeres = it; mujeres(it)},
                    isError = personasTotal,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Personas",
                            modifier = Modifier.size(17.dp)
                        )
                    },
                    label = { Text( text = "¿Cuántas mujeres?" ) },
                    placeholder = { Text(text = "", fontSize = 15.sp) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Las fechas de reserva pueden ajustarse según la disponibilidad del albergue. Consulte los términos en recepción.",
                    fontSize = 13.sp
                )
            }
        }
    }
}

private fun ValidarTotal(hombres: Int?, mujeres: Int?) : Boolean{
    val numHombres = hombres ?: 0
    val numMujeres = mujeres ?: 0
    if(numHombres < 0 && numMujeres < 0) return false
    val total = numHombres + numMujeres
    return total !in 0..10
}

private fun formatDateString(millis: Long): String {
    val date = Date(millis)
    val format = SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    return format.format(date)
}