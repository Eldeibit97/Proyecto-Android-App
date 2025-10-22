package com.example.proyectofinal.componentes

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.utils.FirebaseUtils
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

@Preview(showBackground = true)
@Composable
fun RegisterCard(
    registrar: () -> Unit = {}
) {
    var nombre by rememberSaveable { mutableStateOf("") }
    var apellido by rememberSaveable { mutableStateOf("") }
    var nacimiento by rememberSaveable { mutableStateOf("") }
    var selGenero by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }
    var aceptoTerminos by rememberSaveable { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    var errorFecha by rememberSaveable { mutableStateOf(false) }
    var errorTelefono by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current
    // Regex para formato de fecha dd/mm/aaaa
    val regexFecha = Regex("^([0][1-9]|[12][0-9]|3[01])/([0][1-9]|1[0-2])/([1-2][0-9]{3})\$")
    // 🔹 Regex internacional E.164 (+código y hasta 15 dígitos)
    val regexTelefono = Regex("^\\+[1-9]\\d{6,14}\$")
    val datePickerState = rememberDatePickerState()
    val permitirRegistro by remember { derivedStateOf { validarRegistro(nombre, apellido, nacimiento, selGenero, telefono, aceptoTerminos) } }

    Card(modifier = Modifier.padding(horizontal = 15.dp).fillMaxWidth()) {
        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    Button(onClick = {
                        val selectDate = datePickerState.selectedDateMillis
                        if (selectDate != null) {
                            nacimiento = formatDateString(selectDate) ?: ""
                        }
                        showDatePicker = false
                    }) {
                        Text(text = "confirmar")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDatePicker = false }) {
                        Text(text = "cancelar")
                    }
                }) {
                DatePicker(state = datePickerState)
            }
        }
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Únete a nuestra familia", fontSize = 18.sp)
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                "Crea tu cuenta para utilizar los servicios de los albergues",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(8.dp))

            Column(
                modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                // Nombre
                Text("Nombre", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Person,
                            contentDescription = "Nombre",
                            modifier = Modifier.size(17.dp)
                        )
                    },
                    label = { Text("Nombre") },
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.padding(5.dp))

                // Apellido
                Text("Apellido", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                OutlinedTextField(
                    value = apellido,
                    onValueChange = { apellido = it },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Person,
                            contentDescription = "Apellido",
                            modifier = Modifier.size(17.dp)
                        )
                    },
                    label = { Text("Apellido") },
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = "Fecha de nacimiento",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                OutlinedTextField(
                    value = nacimiento,
                    onValueChange = {
                        nacimiento = it
                        errorFecha = it.isNotEmpty() && !regexFecha.matches(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .pointerInput(nacimiento) {
                            awaitEachGesture {
                                awaitFirstDown(pass = PointerEventPass.Initial)
                                val upEvent =
                                    waitForUpOrCancellation(pass = PointerEventPass.Initial)
                                if (upEvent != null) {
                                    showDatePicker = true
                                }
                            }
                        },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.CalendarMonth,
                            contentDescription = "Fecha de nacimiento",
                            modifier = Modifier.size(17.dp)
                        )
                    },
                    isError = errorFecha,
                    label = { Text(text = "Fecha de nacimiento") },
                    placeholder = { Text(text = "DD/MM/AAAA", fontSize = 15.sp) },
                    shape = RoundedCornerShape(10.dp)
                )
                if (errorFecha) {
                    Text(
                        "Formato inválido. Usa dd/mm/aaaa",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 8.dp, top = 2.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))

                // Género
                Text("Género", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Row(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.padding(4.dp).clickable {
                            selGenero = "Masculino"
                        },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = selGenero == "Masculino", onClick = null)
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text("Masculino")
                    }
                    Row(
                        modifier = Modifier.padding(4.dp).clickable {
                            selGenero = "Femenino"
                        },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = selGenero == "Femenino", onClick = null)
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text("Femenino")
                    }
                }

                Spacer(modifier = Modifier.padding(5.dp))

                // 🔹 Teléfono (solo validación, sin autocompletar)
                Text("Número de teléfono", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                OutlinedTextField(
                    value = telefono,
                    onValueChange = {
                        telefono = it
                        errorTelefono = telefono.isNotEmpty() && !regexTelefono.matches(telefono)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    isError = errorTelefono,
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Phone,
                            contentDescription = "Teléfono",
                            modifier = Modifier.size(17.dp)
                        )
                    },
                    label = { Text("Teléfono") },
                    placeholder = {
                        Text(
                            "Ejemplo: +521234567890 o +14155552671",
                            fontSize = 13.sp
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )
                if (errorTelefono) {
                    Text(
                        "Formato inválido. Usa formato internacional: +(lada)8123456789",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 8.dp, top = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))
            AvisoPrivacidadModal(avisoRespuesta = { aceptoTerminos = it })
            Spacer(modifier = Modifier.padding(6.dp))
            // Botón de registro
            Button(
                onClick = {
                    if (!aceptoTerminos) {
                        Toast.makeText(
                            context,
                            "Debes aceptar los términos y condiciones para continuar",
                            Toast.LENGTH_LONG
                        ).show()
                        return@Button
                    }

                    if (nombre.isBlank() || apellido.isBlank() || nacimiento.isBlank() ||
                        selGenero.isBlank() || telefono.isBlank()
                    ) {
                        Toast.makeText(
                            context,
                            "Por favor completa todos los campos",
                            Toast.LENGTH_LONG
                        ).show()
                        return@Button
                    }

                    if (!regexFecha.matches(nacimiento)) {
                        Toast.makeText(
                            context,
                            "La fecha debe tener formato dd/mm/aaaa",
                            Toast.LENGTH_LONG
                        ).show()
                        return@Button
                    }

                    if (!regexTelefono.matches(telefono)) {
                        Toast.makeText(
                            context,
                            "El número debe estar en formato internacional (+<código><número>)",
                            Toast.LENGTH_LONG
                        ).show()
                        return@Button
                    }

                    saveUser(
                        nombre = nombre,
                        apellido = apellido,
                        nacimiento = nacimiento,
                        genero = selGenero,
                        telefono = telefono,
                        onSuccess = {
                            Toast.makeText(
                                context,
                                "Usuario registrado ✅",
                                Toast.LENGTH_SHORT
                            ).show()
                            registrar()
                        },
                        onError = { msg ->
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                        }
                    )
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
                enabled = permitirRegistro,
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3CB93A),
                    contentColor = Color.White
                )
            ) {
                Text("Crear cuenta")
            }
        }
    }
}

private fun validarRegistro(nombre: String, apellido: String, nacimiento: String, genero: String, celular: String, respuesta: Boolean): Boolean {
    return (nombre.isNotBlank() && apellido.isNotBlank() && nacimiento.isNotBlank() && genero.isNotBlank() && celular.isNotBlank() && respuesta)
}

private fun formatDateString(millis: Long): String {
    val date = Date(millis)
    val format = SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault()).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    return format.format(date)
}
// Guardar usuario en Firestore
fun saveUser(
    nombre: String,
    apellido: String,
    nacimiento: String,
    genero: String,
    telefono: String,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val db = FirebaseUtils.db
    val user = hashMapOf(
        "nombre" to nombre,
        "apellido" to apellido,
        "nacimiento" to nacimiento,
        "genero" to genero,
        "telefono" to telefono,
        "timestamp" to System.currentTimeMillis()
    )
    db.collection("users")
        .add(user)
        .addOnSuccessListener { onSuccess() }
        .addOnFailureListener { e -> onError(e.message ?: "Error al registrar usuario") }
}
