package com.example.proyectofinal.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectofinal.R
import com.example.proyectofinal.componentes.DateSelector
import com.example.proyectofinal.modelos.Albergue




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationRequestScreen(
    albergue: Albergue = Albergue(),
    albergueName: String = albergue.nombre,
    ratingText: String = "5.0 (4)",
    hostTag: String = "Superanfitrión",
    dates: String = "21–23 de nov de 2025",
    participants: String = "1 adulto",
    price: String = "$3,365.72, impuestos incluidos",
    onModifyDates: () -> Unit = {},
    onModifyParticipants: () -> Unit = {},
    onShowPriceDetails: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    // Estados para campos (igual sintaxis que TransportRequestScreen)
    var nombre by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }

    // dropdown participantes (mismo estilo que TransportRequestScreen -> personas)
    var personasHombres by rememberSaveable { mutableStateOf("") }
    var personasMujeres by rememberSaveable { mutableStateOf("") }
    var expandedHombres by rememberSaveable { mutableStateOf(false) }
    val opcionesHombres = listOf("No Aplica", "1 persona", "2 personas", "3 personas", "4 personas", "5 o más")
    var expandedMujeres by rememberSaveable { mutableStateOf(false) }
    val opcionesMujeres = listOf("No Aplica", "1 persona", "2 personas", "3 personas", "4 personas", "5 o más")
    var fecha by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    Scaffold(

    ) { innerPadding ->
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Crea tu Reserva",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(12.dp))
            // Card principal con detalle de reservación
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    // Imagen (ajusta el drawable a tu recurso: R.drawable.reserva_posada)
                    Image(
                        painter = painterResource(id = R.drawable.pdp),
                        contentDescription = "Foto del albergue",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)) {
                        // Título y rating
                        Text(
                            text = "Caritas Centro",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Av. Constitución 1234, Centro, Monterrey, Nuevo León", fontSize = 13.sp)
                            Spacer(modifier = Modifier.width(12.dp))
                            /*Text(text = "• $hostTag", fontSize = 13.sp)*/
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        // Fechas
                        Text(text = "Fechas de reserva",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp)

                        DateSelector { startDate, endDate ->
                            // Aquí recibes las fechas seleccionadas
                            println("Fecha de llegada: $startDate")
                            if (endDate != null) {
                                println("Fecha de salida: $endDate")
                            }
                        }

                        // Participantes
                        Text(
                            text = "Cantidad de personas",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Hombres",
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        ExposedDropdownMenuBox(
                            expanded = expandedHombres,
                            onExpandedChange = { expandedHombres = !expandedHombres },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                value = if (personasHombres.isBlank()) "¿Cuántas personas?" else personasHombres,
                                onValueChange = { /* no editable */ },
                                readOnly = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Person,
                                        contentDescription = "Personas",
                                        modifier = Modifier.size(17.dp)
                                    )
                                },
                                placeholder = { Text(text = "¿Cuántas personas?", fontSize = 15.sp) },
                                shape = RoundedCornerShape(10.dp),
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedHombres) },
                                modifier = Modifier
                                    .menuAnchor()         // <- aquí: marca el anchor para el dropdown
                                    .fillMaxWidth()
                            )

                            ExposedDropdownMenu(
                                expanded = expandedHombres,
                                onDismissRequest = { expandedHombres = false }
                            ) {
                                opcionesHombres.forEach { opcion ->
                                    DropdownMenuItem(
                                        text = { Text(opcion) },
                                        onClick = {
                                            personasHombres = opcion
                                            expandedHombres = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Mujeres",
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        ExposedDropdownMenuBox(
                            expanded = expandedMujeres,
                            onExpandedChange = { expandedMujeres = !expandedMujeres },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                value = if (personasMujeres.isBlank()) "¿Cuántas personas?" else personasMujeres,
                                onValueChange = { /* no editable */ },
                                readOnly = true,
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Person,
                                        contentDescription = "Personas",
                                        modifier = Modifier.size(17.dp)
                                    )
                                },
                                placeholder = { Text(text = "¿Cuántas personas?", fontSize = 15.sp) },
                                shape = RoundedCornerShape(10.dp),
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedMujeres) },
                                modifier = Modifier
                                    .menuAnchor()         // <- aquí: marca el anchor para el dropdown
                                    .fillMaxWidth()
                            )

                            ExposedDropdownMenu(
                                expanded = expandedMujeres,
                                onDismissRequest = { expandedMujeres = false }
                            ) {
                                opcionesMujeres.forEach { opcion ->
                                    DropdownMenuItem(
                                        text = { Text(opcion) },
                                        onClick = {
                                            personasMujeres = opcion
                                            expandedMujeres = false
                                        }
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        // Política de cancelación
                        /*Text(text = "Cancelación gratuita", fontWeight = FontWeight.Bold)*/
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Las fechas y cantidad de personas pueden ajustarse según la disponibilidad del albergue. Consulte los términos en recepción.",
                            fontSize = 13.sp
                        )/*
                        Text(
                            text = "Política completa",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .clickable { /* abrir política */ }
                        )*/

                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
            // Botones de acción (Enviar / Limpiar)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = {

                }) {
                    Text(text = "Limpiar",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color(0xFF367BD0))
                }

                Button(
                    onClick = {

                    },
                    modifier = Modifier,
                    enabled = true,
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonColors(
                        containerColor = Color(0xFFEF3F3F),
                        contentColor = Color(0xFFFFFFFF),
                        disabledContainerColor = Color(0xFF9A9A9A),
                        disabledContentColor = Color(0xFFFFFFFF)
                    )

                ) {
                    Text(text = "Reservar")
                }

            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
fun PreviewReservationRequestScreen() {
    ReservationRequestScreen(
        onModifyDates = { /* preview */ },
        onModifyParticipants = { /* preview */ },
        onShowPriceDetails = { /* preview */ },
        onNext = { /* preview siguiente */ }
    )
}
