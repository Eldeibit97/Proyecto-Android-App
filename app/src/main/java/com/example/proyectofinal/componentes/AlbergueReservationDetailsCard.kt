package com.example.proyectofinal.componentes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.WatchLater
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun AlbergueReservationDetailsCard(modifier: Modifier = Modifier,
                                   albergue: Albergue? = Albergue(),
                                   expand: Boolean = true){
    Card(modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = albergue?.nombre ?: "",
                modifier = Modifier.padding(vertical = 3.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }
        AnimatedVisibility(visible = expand) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pdp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(width = 200.dp)
                        .clip(shape = RoundedCornerShape(10.dp)),
                    contentDescription = "Foto del albergue"
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 5.dp, top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = "Direccion",
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = albergue?.direccion ?: "",
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 6.dp)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Phone,
                        contentDescription = "Celular",
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = "+52 ${albergue?.celular ?: 0}",
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 6.dp)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.WatchLater,
                        contentDescription = "Horario",
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = "Todos los dias, a cualquier hora.",
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 6.dp)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.People,
                        contentDescription = "Cupo",
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = "${albergue?.disponibilidad ?: 0}/${albergue?.capacidad ?: 60}",
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 6.dp)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Servicios:",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}