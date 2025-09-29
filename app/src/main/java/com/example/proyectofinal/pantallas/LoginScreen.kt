package com.example.proyectofinal.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.R
import com.example.proyectofinal.componentes.LoginCard
import com.example.proyectofinal.componentes.RegisterCard

@Preview(showBackground = true)
@Composable
fun LoginScreen(aHome:() -> Unit = {}) {

    var section by rememberSaveable { mutableStateOf(value = "") }
    var enableB by rememberSaveable { mutableStateOf(value = false) }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.cmlogo),
            modifier = Modifier.size(180.dp),
            contentDescription = "Logo de caritas")
        Text(text = "Caritas de Monterrey",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(7.dp))
        Text(text = "Conectando corazones, transformando vidas",
            fontSize = 14.sp)
        Spacer(modifier = Modifier.padding(15.dp))
        Card(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 10.dp)
            .height(35.dp),
            shape = RoundedCornerShape(30.dp),) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {section = "login"; enableB = false},
                    enabled = enableB,
                    colors = ButtonColors(
                        containerColor = Color(0xFFC5C5C5),
                        contentColor = Color(0xFF000000),
                        disabledContainerColor = Color(0xFFF1F1F1),
                        disabledContentColor = Color(0xFF000000)
                    ),
                    contentPadding = PaddingValues(horizontal = 45.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "Iniciar Sesión"
                    )
                }
                Button(
                    onClick = {section = "register"; enableB = true},
                    enabled = !enableB,
                    colors = ButtonColors(
                        containerColor = Color(0xFFC5C5C5),
                        contentColor = Color(0xFF000000),
                        disabledContainerColor = Color(0xFFF1F1F1),
                        disabledContentColor = Color(0xFF000000)
                    ),
                    contentPadding = PaddingValues(horizontal = 45.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "Registrarse"
                    )
                }
            }
        }
        if(section == "register"){
            RegisterCard(avanzar = aHome)
        }else{
            LoginCard(avanzar = aHome)
        }
        Spacer(modifier = Modifier.padding(15.dp))
        Text(text = "Al continuar, aceptas los términos de uso del servicio y politica de privacidad de Caritas de Monterrey.",
            modifier = Modifier.padding(horizontal = 10.dp),
            fontSize = 14.sp,
            textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = "©2025 Caritas de Monterrey - Transformando vidas",
            modifier = Modifier.padding(horizontal = 10.dp),
            fontSize = 12.sp,
            textAlign = TextAlign.Center)
    }
}