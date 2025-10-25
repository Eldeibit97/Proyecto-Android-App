package com.example.proyectofinal.modelos

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.FirebaseAuth


data class Reserva(
    val id : String = "",
    val nombreAlbergue : String = "",
    val nombreResponsable : String = "",
    val apellidoResponsable : String = "",
    val celular : String = "",
    val fechaLlegada : String = "",
    val fechaSalida : String = "",
    val numHombres : Int = 1,
    val numMujeres : Int = 1,
    val numPersonas : Int = 1,
)

suspend fun fetchReservas(): List<Reserva> {
    return try {
        val db = FirebaseFirestore.getInstance()
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user == null) {
            println("⚠️ No hay usuario autenticado")
            return emptyList()
        }

        val uid = user.uid
        println("🔥 Buscando reservas del UID: $uid")

        // 🔹 Filtra solo documentos que pertenezcan al usuario autenticado
        val snapshot = db.collection("reservations")
            .whereEqualTo("uid", uid)
            .get()
            .await()

        println("📦 Documentos encontrados: ${snapshot.size()}")

        snapshot.documents.map { doc ->
            Reserva(
                id = doc.id,
                nombreAlbergue = doc.getString("albergue") ?: "",
                nombreResponsable = doc.getString("nombre") ?: "",
                apellidoResponsable = doc.getString("apellido") ?: "",
                celular = doc.getString("celular") ?: "",
                fechaLlegada = doc.getString("fechaLlegada") ?: "",
                fechaSalida = doc.getString("fechaSalida") ?: "",
                numHombres = (doc.getLong("hombres") ?: 0L).toInt(),
                numMujeres = (doc.getLong("mujeres") ?: 0L).toInt(),
                numPersonas = (doc.getLong("numPersonas") ?: 0L).toInt()
            )
        }
    } catch (e: Exception) {
        e.printStackTrace()
        println("❌ Error al obtener reservas: ${e.message}")
        emptyList()
    }
}

