package com.example.proyectofinal.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
fun RegisterCard(avanzar : () -> Unit =  {}, celular: (Long) -> Unit = {}, nombre: (String) -> Unit = {},
                 apellido: (String) -> Unit = {}, nacimiento: (String) -> Unit = {}, genero: (String) -> Unit = {}){

    var nombre by rememberSaveable { mutableStateOf("") }
    var apellido by rememberSaveable { mutableStateOf("") }
    var nacimiento by rememberSaveable { mutableStateOf("") }
    var selGenero by rememberSaveable { mutableStateOf("") }
    var celular by rememberSaveable { mutableStateOf("") }

    Card (modifier = Modifier.padding(horizontal = 15.dp).fillMaxWidth()){
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
                Text(text = "Nombre",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp)
                OutlinedTextField(value = nombre,
                    onValueChange = {nombre = it; nombre(it)},
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {Icon(imageVector = Icons.Outlined.Person,
                        contentDescription = "Nombre",
                        modifier = Modifier.size(17.dp))},
                    label = { Text(text = "Nombre") },
                    placeholder = {Text(text="", fontSize = 15.sp)},
                    shape = RoundedCornerShape(10.dp))
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "Apellido",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp)
                OutlinedTextField(value = apellido,
                    onValueChange = {apellido = it; apellido(it)},
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {Icon(imageVector = Icons.Outlined.Person,
                        contentDescription = "Apellido",
                        modifier = Modifier.size(17.dp))},
                    label = { Text(text = "Apellido") },
                    placeholder = {Text(text="", fontSize = 15.sp)},
                    shape = RoundedCornerShape(10.dp))
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "Fecha de nacimiento",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp)
                OutlinedTextField(value = nacimiento,
                    onValueChange = {nacimiento = it; nacimiento(it)},
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {Icon(imageVector = Icons.Outlined.CalendarMonth,
                        contentDescription = "Fecha de nacimiento",
                        modifier = Modifier.size(17.dp))},
                    label = { Text(text = "Fecha de Nacimiento") },
                    placeholder = {Text(text="dd/mm/aaaa", fontSize = 15.sp)},
                    shape = RoundedCornerShape(10.dp))
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "Género",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp)
                Row(modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Row(modifier = Modifier.padding(4.dp)
                        .clickable(onClick = {selGenero = "Masculino"; genero("Masculino")}),
                        verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = when(selGenero) {
                            "Masculino" -> true
                            else -> false
                        }, onClick = null)
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Masculino")
                    }
                    Row(modifier = Modifier.padding(4.dp)
                        .clickable(onClick = {selGenero = "Femenino"; genero("Femenino")}),
                        verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(selected = when(selGenero) {
                            "Femenino" -> true
                            else -> false
                        }, onClick = null)
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Femenino")
                    }
                }
                Spacer(modifier = Modifier.padding(5.dp))
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
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = "Al continuar, aceptas los términos de uso del servicio y politica de privacidad de Caritas de Monterrey.",
                modifier = Modifier.padding(horizontal = 10.dp),
                fontSize = 14.sp,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.padding(5.dp))
            Button(onClick = avanzar,
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