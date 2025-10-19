package com.example.proyectofinal.componentes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.proyectofinal.modelos.Noticia
import com.example.proyectofinal.modelos.getNoticias

@Preview(showBackground = true)
@Composable
fun NoticiaDisplayCard(noticia: Noticia = getNoticias()[3]){
    var visible by remember { mutableStateOf(false) }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)
        .clickable(onClick = { visible = !visible }),
        shape = RoundedCornerShape(6.dp)
    ) {
        Column(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp,
                start = 18.dp, end = 8.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(shape = RoundedCornerShape(4.dp),
                    colors = CardColors(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.onPrimaryContainer,
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.onPrimaryContainer)
                ) {
                    Text(text = noticia.tipo,
                        modifier = Modifier.padding(vertical = 1.dp, horizontal = 8.dp),
                        fontSize = 14.sp)
                }
                Text(text = "Fecha de publicacíon: ${noticia.fecha}",
                    modifier = Modifier.padding(horizontal = 8.dp),
                    fontSize = 10.sp)
            }
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start) {
                Text(text = noticia.titulo,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold)
                Text(text = noticia.descripcion,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.fillMaxWidth())
                AnimatedVisibility(visible = visible) {
                    Text(text = noticia.cuerpo,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.fillMaxWidth())
                }
            }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(text = noticia.autor,
                    fontSize = 12.sp)
            }
        }
    }
}