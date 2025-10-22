package com.example.proyectofinal.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun LoginCard(Login: () -> Unit = {}, celular: (Long) -> Unit = {}){

    var celular by remember { mutableStateOf("") }

    Card (modifier = Modifier.padding(horizontal = 15.dp).fillMaxWidth()){
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center){
                Icon(imageVector = Icons.Outlined.VerifiedUser,
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
            Text(text = "Ingrese su número de telefono para acceder a su cuenta",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.padding(8.dp))
            Column (modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center){
                Text(text = "Número de telefono",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp)
                OutlinedTextField(value = celular,
                    onValueChange = {celular = it; celular(it.toLong())},
                    leadingIcon = {Icon(imageVector = Icons.Outlined.Phone,
                        contentDescription = "Celular",
                        modifier = Modifier.size(17.dp))},
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Telefono") },
                    placeholder = {Text(text="ej. 5212345678", fontSize = 15.sp)},
                    shape = RoundedCornerShape(10.dp))
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick = Login,
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