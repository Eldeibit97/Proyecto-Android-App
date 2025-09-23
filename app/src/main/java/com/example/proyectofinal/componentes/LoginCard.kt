package com.example.proyectofinal.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun LoginCard(){
    Card (modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth()){
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "Acceso Seguro")
            Spacer(modifier = Modifier.padding(3.dp))
            Text(text = "Ingrese sus credenciales para acceder a su cuenta",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp)
            Spacer(modifier = Modifier.padding(5.dp))
            Column (modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center){
                Text(text = "Correo Electrónico",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp)
                OutlinedTextField(value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {Text(text="ejemploCorreo@gmail.com", fontSize = 12.sp)},
                    shape = RoundedCornerShape(10.dp))
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "Contraseña",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp)
                OutlinedTextField(value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {Text(text="Contraseña", fontSize = 12.sp)},
                    shape = RoundedCornerShape(10.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick = {},
                modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                colors = ButtonColors(
                    containerColor = Color(0xFFEF3F3F),
                    contentColor = Color(0xFFFFFFFF),
                    disabledContainerColor = Color(0xFF9A9A9A),
                    disabledContentColor = Color(0xFFFFFFFF)
                ))
            {
                Text(text = "Iniciar Sesión")
            }
        }
    }
}