package com.example.proyectofinal.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapsCard() {
    // 📍 Coordenadas de los tres albergues
    val posadaDelPeregrino = LatLng(25.6833433710119, -100.34547089691362)
    val divinaProvidencia = LatLng(25.668389672297707, -100.30311694417804)
    val apodaca = LatLng(25.79156194467012, -100.13871492023976)

    // 📍 Posición inicial de la cámara (centrada en Monterrey)
    val monterrey = LatLng(25.6866, -100.3161)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(monterrey, 11.5f)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 15.dp, vertical = 10.dp)
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxWidth().height(300.dp),
            cameraPositionState = cameraPositionState
        ) {
            // 📍 Marker 1: Posada del Peregrino
            MarkerInfoWindow(
                state = rememberMarkerState(position = posadaDelPeregrino),
                title = "Posada del Peregrino",
                snippet = "Albergue en Monterrey"
            )

            // 📍 Marker 2: Divina Providencia
            MarkerInfoWindow(
                state = rememberMarkerState(position = divinaProvidencia),
                title = "Divina Providencia",
                snippet = "Albergue en Guadalupe"
            )

            // 📍 Marker 3: Apodaca
            MarkerInfoWindow(
                state = rememberMarkerState(position = apodaca),
                title = "Apodaca",
                snippet = "Albergue en Apodaca"
            )
        }
    }
}
