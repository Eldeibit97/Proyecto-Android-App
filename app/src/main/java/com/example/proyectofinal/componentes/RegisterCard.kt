package com.example.proyectofinal.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun RegisterCard(avanzar : () -> Unit =  {}){
    Card (modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth()){
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "Únete a nuestra familia",
                fontSize = 18.sp)
            Spacer(modifier = Modifier.padding(6.dp))
            Text(text = "Crea tu cuenta para utilizar los servicios de los albergues",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.padding(8.dp))
            Column (modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center){
                Text(text = "Nombre Completo",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp)
                OutlinedTextField(value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {Icon(imageVector = Icons.Outlined.Person,
                        contentDescription = "Usuario",
                        modifier = Modifier.size(17.dp))},
                    placeholder = {Text(text="Nombre Completo", fontSize = 15.sp)},
                    shape = RoundedCornerShape(10.dp))
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "Número de telefono",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp)
                OutlinedTextField(value = "",
                    onValueChange = {},
                    leadingIcon = {Icon(imageVector = Icons.Outlined.Phone,
                        contentDescription = "Celular",
                        modifier = Modifier.size(17.dp))},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {Text(text="Telefono", fontSize = 15.sp)},
                    shape = RoundedCornerShape(10.dp))
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "Correo Electronico",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp)
                OutlinedTextField(value = "",
                    onValueChange = {},
                    leadingIcon = {Icon(imageVector = Icons.Outlined.Mail,
                        contentDescription = "Mail",
                        modifier = Modifier.size(17.dp))},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {Text(text="ejemploCorreo@gmail.com", fontSize = 15.sp)},
                    shape = RoundedCornerShape(10.dp))
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "Contraseña",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp)
                OutlinedTextField(value = "",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {Icon(imageVector = Icons.Outlined.Lock,
                        contentDescription = "Contraseña",
                        modifier = Modifier.size(17.dp))},
                    placeholder = {Text(text="Contraseña", fontSize = 15.sp)},
                    shape = RoundedCornerShape(10.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick = {avanzar},
                modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                colors = ButtonColors(
                    containerColor = Color(0xFF3CB93A),
                    contentColor = Color(0xFFFFFFFF),
                    disabledContainerColor = Color(0xFF9A9A9A),
                    disabledContentColor = Color(0xFFFFFFFF)
                ))
            {
                Text(text = "Crear Cuenta")
            }
        }
    }
}