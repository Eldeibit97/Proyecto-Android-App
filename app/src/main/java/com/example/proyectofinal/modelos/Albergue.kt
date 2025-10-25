package com.example.proyectofinal.modelos

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

data class Albergue(
    val id : Int = 0,
    val nombre : String = "Posada del Peregrino",
    val celular : Long = 8113402208,
    val capacidad : Int = 60,
    val cuota : Int = 30,
    val disponibilidad : Int = 0,
    val direccion : String = "Franciso G. Sada, Av. Simón Bolívar 190, Deportivo Obispado, Chepevera, 64030 Monterrey, N.L.",
    val latitud : Double = 25.6833433710119,
    val longitud : Double = -100.34547089691362,
    val servicios : List<Servicios> = listOf(Servicios(id = 1, nombre = "Duchas", precio = "10"),
        Servicios(id = 2, nombre = "Desayuno", precio = "15"), Servicios(id = 3, nombre = "Comida", precio = "15"),
        Servicios(id = 4, nombre = "Cena", precio = "10"),Servicios(id = 5, nombre = "Lavanderia", precio = "10"),
        Servicios(id = 6, nombre = "Consulta Medica", precio = "Gratis"), Servicios(id = 7, nombre = "Traslado", precio = "20"))
)

suspend fun fetchAlbergues(): List<Albergue> {
    val db = FirebaseFirestore.getInstance()
    val listaAlbergues = mutableListOf<Albergue>()

    try {
        val snapshot = db.collection("Albergues")
            .orderBy("ID", Query.Direction.DESCENDING)
            .get()
            .await()

        for (doc in snapshot.documents) {
            val iD = doc.getLong("ID")?.toInt() ?: 0
            val nombre = doc.getString("Nombre") ?: ""
            val celular = doc.getLong("Celular")
            val capacidad = doc.getLong("Capacidad")?.toInt() ?: 0
            val cuota = doc.getLong("Cuota")?.toInt() ?: 0
            val direccion = doc.getString("Dirección") ?: ""
            val mapa = doc.getGeoPoint("Mapa") ?: GeoPoint(0.0, 0.0)

            listaAlbergues.add(
                Albergue(
                    id = iD,
                    nombre = nombre,
                    celular = celular ?: 0,
                    direccion = direccion,
                    latitud = mapa.latitude,
                    longitud = mapa.longitude,
                    capacidad = capacidad,
                    cuota = cuota,
                )
            )
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return listaAlbergues
}