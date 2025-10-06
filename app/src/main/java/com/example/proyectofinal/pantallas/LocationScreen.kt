package com.example.proyectofinal.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowCircleRight
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.R
import com.example.proyectofinal.componentes.AlbergueInfoCard
import com.example.proyectofinal.componentes.MapsCard
import com.example.proyectofinal.modelos.getAlbergues

@Preview(showBackground = true)
@Composable
fun LocationScreen(aTaxi: () -> Unit = {}){
    Column(modifier = Modifier.fillMaxSize()){
        Spacer(modifier = Modifier.height(20.dp))
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 2.dp)){
            Image(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "Foto del mapa",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(760.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Crop
            )
        }
        Row(modifier = Modifier.fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 15.dp)
            .height(height = 50.dp)
            .width(width = 10.dp),
            horizontalArrangement = Arrangement.Start) {
            Button(onClick = aTaxi,
                modifier = Modifier.width(width = 150.dp),
                colors = ButtonColors(
                    containerColor = Color(0xFFEF3F3F),
                    contentColor = Color(0xFFFFFFFF),
                    disabledContainerColor = Color(0xFF9A9A9A),
                    disabledContentColor = Color(0xFFFFFFFF)
                ),
                contentPadding = PaddingValues(horizontal = 10.dp,vertical = 2.dp),
                shape = RoundedCornerShape(8.dp)){
                Icon(imageVector = Icons.Outlined.DirectionsCar,
                    contentDescription = "transporte",
                    modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "Solicitar transporte",
                    fontSize = 18.sp)
            }
        }

    }
}