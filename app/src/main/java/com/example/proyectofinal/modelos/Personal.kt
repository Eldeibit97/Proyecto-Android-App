package com.example.proyectofinal.modelos

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

data class PersonalInfo(
    val id: String = "",
    val nombre: String = "Juan",
    val apellido: String = "Perez",
    val telefono: String = "+52 81 1384 4318",
    val origen: String = "Tecnologico de Monterrey",
    val destino: String = "Posada del Peregrino",
    val fecha: String = "23/Octubre/2025",
    val hora: String = "19:00",
    val personas: String = "No encontrado"
)

suspend fun fetchTransporte(): List<PersonalInfo> {
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


        // 🔹 Obtiene solo los documentos del usuario actual
        val snapshot = db.collection("transporte")
            .whereEqualTo("uid", uid) // 👈 asegúrate que en Firestore el campo se llame exactamente "uid"
            .get()
            .await()

        println("📦 Documentos encontrados en transporte: ${snapshot.size()}")

        // 🔹 Mapea los documentos Firestore a objetos PersonalInfo
        snapshot.documents.map { doc ->
            println("✅ Documento transporte encontrado: ${doc.id} → ${doc.data}")
            PersonalInfo(
                id = doc.id,
                nombre = doc.getString("nombre") ?: "",
                apellido = doc.getString("apellido") ?: "",
                telefono = doc.getString("telefono") ?: "",
                origen = doc.getString("origen") ?: "",
                destino = doc.getString("destino") ?: "",
                fecha = doc.getString("timestamp") ?: "",
                hora = doc.getString("momentoInicio") ?: "",
                personas = doc.getString("personas") ?: "",
            )
        }
    } catch (e: Exception) {
        e.printStackTrace()
        println("❌ Error al obtener transporte: ${e.message}")
        emptyList()
    }
}
