package com.example.proyectofinal.componentes

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

@Preview(showBackground = true)
@Composable
fun LoginCard(Login: () -> Unit = {}, celularCallback: (Long) -> Unit = {}) {

    val context = LocalContext.current
    val activity = context as? Activity

    var celular by remember { mutableStateOf("") }
    var verificationId by remember { mutableStateOf<String?>(null) }
    var code by remember { mutableStateOf("") }
    var codeSent by remember { mutableStateOf(false) }

    val auth = FirebaseAuth.getInstance()

    Card(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.VerifiedUser,
                    contentDescription = "Icono de Seguridad",
                    modifier = Modifier.size(20.dp),
                    tint = Color(0xFF40C23F)
                )
                Text(
                    text = "Acceso Seguro",
                    modifier = Modifier.padding(horizontal = 4.dp),
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = if (!codeSent)
                    "Ingrese su número de teléfono para acceder a su cuenta"
                else
                    "Ingrese el código de verificación enviado a su número",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(8.dp))

            if (!codeSent) {
                OutlinedTextField(
                    value = celular,
                    onValueChange = { celular = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Phone,
                            contentDescription = "Celular",
                            modifier = Modifier.size(17.dp)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Teléfono (incluye lada, ej. +521234567890)") },
                    placeholder = { Text(text = "+52...", fontSize = 15.sp) },
                    shape = RoundedCornerShape(10.dp)
                )
            } else {
                OutlinedTextField(
                    value = code,
                    onValueChange = { code = it },
                    label = { Text("Código de verificación") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                )
            }

            Spacer(modifier = Modifier.padding(5.dp))

            Button(
                onClick = {
                    if (!codeSent) {
                        if (celular.isBlank()) {
                            Toast.makeText(context, "Por favor ingrese su número de teléfono", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        celularCallback(celular.filter { it.isDigit() }.toLongOrNull() ?: 0)

                        val options = PhoneAuthOptions.newBuilder(auth)
                            .setPhoneNumber(celular)
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(activity!!)
                            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                                    auth.signInWithCredential(credential)
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                Toast.makeText(context, "Inicio de sesión exitoso ✅", Toast.LENGTH_SHORT).show()
                                                Login()
                                            } else {
                                                Toast.makeText(context, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                }

                                override fun onVerificationFailed(e: FirebaseException) {
                                    Log.e("Auth", "Error de verificación", e)
                                    Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                                }

                                override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                                    verificationId = id
                                    codeSent = true
                                    Toast.makeText(context, "Código enviado", Toast.LENGTH_SHORT).show()
                                }
                            })
                            .build()

                        PhoneAuthProvider.verifyPhoneNumber(options)
                    } else {
                        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
                        auth.signInWithCredential(credential)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(context, "Verificación exitosa ✅", Toast.LENGTH_SHORT).show()
                                    Login()
                                } else {
                                    Toast.makeText(context, "Código incorrecto ❌", Toast.LENGTH_SHORT).show()
                                }
                            }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                colors = ButtonColors(
                    containerColor = Color(0xFFEF3F3F),
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White
                )
            ) {
                Text(text = if (!codeSent) "Enviar código" else "Verificar código")
            }
        }
    }
}