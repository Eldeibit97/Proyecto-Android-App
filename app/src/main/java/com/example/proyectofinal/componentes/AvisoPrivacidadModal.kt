package com.example.proyectofinal.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.modelos.Aviso_Privacidad

@Preview(showBackground = true)
@Composable
fun AvisoPrivacidadModal(avisoRespuesta: (Boolean) -> Unit = {}){
    var showDialog by remember { mutableStateOf(false) }
    var aceptar by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(showDialog) {
            AlertDialog(
                modifier = Modifier.padding(20.dp),
                onDismissRequest = { showDialog = false},
                title = { Text(text = "Aviso de privacidad",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold) },
                text = {
                    Column(modifier = Modifier.verticalScroll(state = scrollState)) {
                        Text(text = Aviso_Privacidad)
                    }
                },
                containerColor = MaterialTheme.colorScheme.surface,
                confirmButton = { Button(onClick = { aceptar = true ; avisoRespuesta(true) ; showDialog = false }){ Text(text = "Aceptar") } },
                dismissButton = { Button(onClick = { showDialog = false }){ Text(text = "Cancelar") } },
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = aceptar, onCheckedChange = { aceptar = it ; avisoRespuesta(it)})
            Text(
                text = "Acepto los términos de uso del servicio y politica de privacidad de Caritas de Monterrey.",
                modifier = Modifier.padding(horizontal = 2.dp),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        TextButton(onClick = { showDialog = true },
            modifier = Modifier.height(25.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(text = "Ver terminos y politica de privacidad",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 14.sp,
                textAlign = TextAlign.Center)
        }
    }
}