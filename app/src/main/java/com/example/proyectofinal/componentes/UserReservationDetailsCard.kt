package com.example.proyectofinal.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun UsuarioReservationDetailsCard() {
    // 🔹 Estados locales
    var nombre by remember { mutableStateOf("Desconocido") }
    var telefono by remember { mutableStateOf("0") }

    // 🔹 Obtener usuario actual
    val user = FirebaseAuth.getInstance().currentUser
    val uid = user?.uid

    // 🔹 Cargar datos de Firestore solo si hay usuario autenticado
    LaunchedEffect(uid) {
        if (uid != null) {
            val db = FirebaseFirestore.getInstance()
            db.collection("users").document(uid).get()
                .addOnSuccessListener { doc ->
                    if (doc.exists()) {
                        nombre = doc.getString("nombre") ?: "Desconocido"
                        telefono = doc.getString("telefono") ?: "0"
                    }
                }
                .addOnFailureListener {
                    nombre = "Desconocido"
                    telefono = "0"
                }
        }
    }

    // 🔹 UI
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Responsable de la reservación",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(4.dp))

            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "Nombre",
                            modifier = Modifier.size(17.dp)
                        )
                    },
                    label = { Text(text = "Nombre") },
                    shape = RoundedCornerShape(10.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    value = telefono,
                    onValueChange = {},
                    readOnly = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Phone,
                            contentDescription = "Celular",
                            modifier = Modifier.size(17.dp)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Teléfono") },
                    shape = RoundedCornerShape(10.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUsuarioReservationDetailsCard() {
    UsuarioReservationDetailsCard()
}
