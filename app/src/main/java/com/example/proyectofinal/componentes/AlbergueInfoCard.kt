package com.example.proyectofinal.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.foundation.Image
import androidx.compose.material.icons.outlined.ArrowCircleRight
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.WatchLater
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.R
import com.example.proyectofinal.modelos.Albergue

@Preview(showBackground = true)
@Composable
fun AlbergueInfoCard(albergue: Albergue = Albergue()){
    Card(modifier = Modifier.fillMaxWidth().padding(15.dp)){
        Row(modifier = Modifier.fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = albergue.nombre,
                modifier = Modifier.padding(vertical = 3.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center)
            Card(colors = CardColors(Color(color = 0xFF3CB93A),
                Color(color = 0xFFFFFFFF),
                Color(color = 0xFFEF3F3F),
                Color(color = 0xFFFFFFFF)),
                shape = RoundedCornerShape(size = 4.dp)
            ){
                Text(text = "Disponible",
                    modifier = Modifier.padding(vertical = 3.dp, horizontal = 5.dp),
                    fontSize = 16.sp)
            }
        }
        Column(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            Image(painter = painterResource(id = R.drawable.pdp),
                modifier = Modifier
                    .fillMaxWidth()
                    .width(width = 200.dp)
                    .clip(shape = RoundedCornerShape(10.dp)),
                contentDescription = "Foto del albergue")
            Row(modifier = Modifier.fillMaxWidth()
                .padding(bottom = 5.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically){
                Icon(imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "Direccion",
                    modifier = Modifier.size(15.dp))
                Text(text = albergue.direccion,
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 6.dp))
            }
            Row(modifier = Modifier.fillMaxWidth()
                .padding(bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically){
                Icon(imageVector = Icons.Outlined.Phone,
                    contentDescription = "Celular",
                    modifier = Modifier.size(15.dp))
                Text(text = "+52 ${albergue.celular}",
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 6.dp))
            }
            Row(modifier = Modifier.fillMaxWidth()
                .padding(bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically){
                Icon(imageVector = Icons.Outlined.WatchLater,
                    contentDescription = "Horario",
                    modifier = Modifier.size(15.dp))
                Text(text = "Lunes a viernes 8:00 AM - 6:00 PM",
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 6.dp))
            }
            Row(modifier = Modifier.fillMaxWidth()
                .padding(bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically){
                Icon(imageVector = Icons.Outlined.People,
                    contentDescription = "Cupo",
                    modifier = Modifier.size(15.dp))
                Text(text = "${albergue.capacidad}/60 personas",
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 6.dp))
            }
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically){
                Text(text = "Servicios:",
                    modifier = Modifier.fillMaxWidth())
            }
        }
        Row(modifier = Modifier.fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 8.dp)
            .height(height = 35.dp),
            horizontalArrangement = Arrangement.Center){
            Button(onClick = {},
                modifier = Modifier.width(width = 180.dp),
                colors = ButtonColors(Color(color = 0xFFFFFFFF),
                    Color(0xFF000000),
                    Color(color = 0xFFFFFFFF),
                    Color(color = 0xFF000000)),
                contentPadding = PaddingValues(horizontal = 10.dp,vertical = 2.dp),
                shape = RoundedCornerShape(8.dp)){
                Icon(imageVector = Icons.Outlined.ArrowCircleRight,
                    contentDescription = "Cómo llegar",
                    modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "Cómo llegar",
                        fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.padding(all = 5.dp))
            Button(onClick = {},
                modifier = Modifier.width(width = 180.dp),
                colors = ButtonColors(Color(color = 0xFF1F67F5),
                    Color(color = 0xFFFFFFFF),
                    Color(color = 0xFFBBBBBB),
                    Color(color = 0xFFFFFFFF)
                ),
                contentPadding = PaddingValues(horizontal = 10.dp,vertical = 2.dp),
                shape = RoundedCornerShape(size = 8.dp)){
                Icon(imageVector = Icons.Filled.CalendarToday,
                    contentDescription = "Hacer reserva",
                    modifier = Modifier.size(size = 20.dp))
                Spacer(modifier = Modifier.padding(all = 5.dp))
                Text(text = "Reservar",
                    fontSize = 18.sp)
            }
        }
    }
}