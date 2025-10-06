package com.example.proyectofinal.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DateSelector(
    onDateSelected: (String, String?) -> Unit // callback con fechaInicio y fechaFin (puede ser null)
) {
    var singleDateMode by remember { mutableStateOf(false) }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(8.dp))

        // Fecha de inicio
        OutlinedTextField(
            value = startDate,
            onValueChange = { startDate = it },
            label = { Text("Fecha de llegada", fontSize = 15.sp) },
            placeholder = { Text("dd/mm/aaaa", fontSize = 15.sp) },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Fecha de fin (solo si selecciona rango)
        if (!singleDateMode) {
            OutlinedTextField(
                value = endDate,
                onValueChange = { endDate = it },
                label = { Text("Fecha de salida", fontSize = 15.sp) },
                placeholder = { Text("dd/mm/aaaa", fontSize = 15.sp) },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(13.dp))

        // Switch para elegir modo
        Row(modifier = Modifier.width(300.dp)) {
            Text("Salida indefinida",
                fontSize = 14.sp)
            Spacer(modifier = Modifier.width(140.dp))
            Switch(
                checked = singleDateMode,
                onCheckedChange = { singleDateMode = it }
            )
        }
        // Botón para confirmar selección
        /*Button(onClick = { onDateSelected(startDate, if (singleDateMode) null else endDate) }) {
            Text("Confirmar fecha")
        }*/
    }
}
