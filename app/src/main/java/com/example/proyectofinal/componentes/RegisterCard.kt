package com.example.proyectofinal.componentes

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.utils.FirebaseUtils
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
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

    // 🔹 Variables de verificación por OTP
    var verificationId by remember { mutableStateOf<String?>(null) }
    var code by remember { mutableStateOf("") }
    var codeSent by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val activity = context as? Activity
    val auth = FirebaseAuth.getInstance()

    val regexFecha = Regex("^([0][1-9]|[12][0-9]|3[01])/([0][1-9]|1[0-2])/([1-2][0-9]{3})$")
    val regexTelefono = Regex("^\\+[1-9]\\d{6,14}$")

    val permitirRegistro by remember {
        derivedStateOf {
            validarRegistro(nombre, apellido, nacimiento, selGenero, telefono, aceptoTerminos)
        }
    }

    // 📅 DatePicker
    val datePickerState = rememberDatePickerState()
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                Button(onClick = {
                    val selectDate = datePickerState.selectedDateMillis
                    if (selectDate != null) nacimiento = formatDateString(selectDate)
                    showDatePicker = false
                }) { Text(text = "Confirmar") }
            },
            dismissButton = {
                Button(onClick = { showDatePicker = false }) { Text(text = "Cancelar") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    // 🧩 Interfaz de registro
    Card(modifier = Modifier.padding(horizontal = 15.dp).fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Únete a nuestra familia", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(8.dp))

            // Campos de entrada
            UserInputFields(
                nombre, { nombre = it },
                apellido, { apellido = it },
                nacimiento, { nacimiento = it },
                selGenero, { selGenero = it },
                telefono, { telefono = it },
                errorTelefono, { errorTelefono = it },
                errorFecha, { errorFecha = it },
                showDatePicker, { showDatePicker = it }
            )

            Spacer(modifier = Modifier.padding(10.dp))
            AvisoPrivacidadModal(avisoRespuesta = { aceptoTerminos = it })
            Spacer(modifier = Modifier.padding(6.dp))

            // 🔹 Campo para OTP
            if (codeSent) {
                OutlinedTextField(
                    value = code,
                    onValueChange = { code = it },
                    label = { Text("Código OTP") },
                    placeholder = { Text("Ingresa el código de 6 dígitos") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.padding(5.dp))
            }

            // 🔘 Botón de enviar código / verificar código
            Button(
                onClick = {
                    if (!codeSent) {
                        // Validación
                        if (!permitirRegistro) {
                            Toast.makeText(context, "Completa todos los campos y acepta los términos", Toast.LENGTH_LONG).show()
                            return@Button
                        }
                        if (!regexTelefono.matches(telefono)) {
                            Toast.makeText(context, "El número debe tener formato internacional (+521234567890)", Toast.LENGTH_LONG).show()
                            return@Button
                        }

                        // Enviar OTP (Firebase lo detectará como número de prueba)
                        val options = PhoneAuthOptions.newBuilder(auth)
                            .setPhoneNumber(telefono)
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(activity!!)
                            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                                    signInAndSaveUser(auth, credential, nombre, apellido, nacimiento, selGenero, telefono, registrar, context)
                                }

                                override fun onVerificationFailed(e: FirebaseException) {
                                    Log.e("Auth", "Error en verificación: ${e.message}")
                                    Toast.makeText(context, "Error al verificar: ${e.message}", Toast.LENGTH_LONG).show()
                                }

                                override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                                    verificationId = id
                                    codeSent = true
                                    Toast.makeText(context, "Código enviado al teléfono", Toast.LENGTH_SHORT).show()
                                }
                            }).build()
                        PhoneAuthProvider.verifyPhoneNumber(options)
                    } else {
                        // Verificar OTP ingresado
                        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
                        signInAndSaveUser(auth, credential, nombre, apellido, nacimiento, selGenero, telefono, registrar, context)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = permitirRegistro,
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!codeSent) Color(0xFF3CB93A) else Color(0xFFEF3F3F),
                    contentColor = Color.White
                )
            ) {
                Text(if (!codeSent) "Registrarse" else "Verificar código")
            }
        }
    }
}

@Composable
fun UserInputFields(
    nombre: String, onNombre: (String) -> Unit,
    apellido: String, onApellido: (String) -> Unit,
    nacimiento: String, onNacimiento: (String) -> Unit,
    genero: String, onGenero: (String) -> Unit,
    telefono: String, onTelefono: (String) -> Unit,
    errorTelefono: Boolean, onErrorTelefono: (Boolean) -> Unit,
    errorFecha: Boolean, onErrorFecha: (Boolean) -> Unit,
    showDatePicker: Boolean, onShowDatePicker: (Boolean) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Nombre
        Text("Nombre", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        OutlinedTextField(
            value = nombre,
            onValueChange = onNombre,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nombre") },
            leadingIcon = { Icon(Icons.Outlined.Person, null) },
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.padding(5.dp))

        // Apellido
        Text("Apellido", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        OutlinedTextField(
            value = apellido,
            onValueChange = onApellido,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Apellido") },
            leadingIcon = { Icon(Icons.Outlined.Person, null) },
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.padding(5.dp))

        // Fecha de nacimiento
        Text("Fecha de nacimiento", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        OutlinedTextField(
            value = nacimiento,
            onValueChange = onNacimiento,
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    awaitEachGesture {
                        awaitFirstDown(pass = PointerEventPass.Initial)
                        val up = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                        if (up != null) onShowDatePicker(true)
                    }
                },
            label = { Text("Fecha de nacimiento") },
            placeholder = { Text("DD/MM/AAAA") },
            leadingIcon = { Icon(Icons.Outlined.CalendarMonth, null) },
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.padding(5.dp))

        // Género
        Text("Género", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Row(modifier = Modifier.clickable { onGenero("Masculino") }, verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = genero == "Masculino", onClick = null)
                Text("Masculino")
            }
            Row(modifier = Modifier.clickable { onGenero("Femenino") }, verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = genero == "Femenino", onClick = null)
                Text("Femenino")
            }
        }

        Spacer(modifier = Modifier.padding(5.dp))

        // Teléfono
        Text("Número de teléfono", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        OutlinedTextField(
            value = telefono,
            onValueChange = {
                onTelefono(it)
                onErrorTelefono(it.isNotEmpty() && !Regex("^\\+[1-9]\\d{6,14}$").matches(it))
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Teléfono (+52...)") },
            leadingIcon = { Icon(Icons.Outlined.Phone, null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            shape = RoundedCornerShape(10.dp)
        )
    }
}

private fun validarRegistro(
    nombre: String, apellido: String, nacimiento: String,
    genero: String, celular: String, respuesta: Boolean
): Boolean {
    return (nombre.isNotBlank() && apellido.isNotBlank() && nacimiento.isNotBlank()
            && genero.isNotBlank() && celular.isNotBlank() && respuesta)
}

private fun formatDateString(millis: Long): String {
    val date = Date(millis)
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return format.format(date)
}

// 🔹 Guarda usuario en Firestore una vez autenticado
fun signInAndSaveUser(
    auth: FirebaseAuth,
    credential: PhoneAuthCredential,
    nombre: String,
    apellido: String,
    nacimiento: String,
    genero: String,
    telefono: String,
    registrar: () -> Unit,
    context: android.content.Context
) {
    auth.signInWithCredential(credential)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = task.result?.user
                val uid = user?.uid ?: return@addOnCompleteListener

                val db = FirebaseUtils.db
                val userData = hashMapOf(
                    "uid" to uid,
                    "nombre" to nombre,
                    "apellido" to apellido,
                    "nacimiento" to nacimiento,
                    "genero" to genero,
                    "telefono" to telefono,
                    "timestamp" to System.currentTimeMillis()
                )

                db.collection("users").document(uid).set(userData)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Registro y verificación completados ✅", Toast.LENGTH_SHORT).show()
                        registrar()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error al guardar: ${e.message}", Toast.LENGTH_LONG).show()
                    }
            } else {
                Toast.makeText(context, "Código incorrecto ❌", Toast.LENGTH_SHORT).show()
            }
        }
}
