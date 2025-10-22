package com.example.proyectofinal.modelos

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

data class Noticia(
    val id: String = "",
    val titulo: String = "",
    val descripcion: String = "",
    val cuerpo: String = "",
    val tipo: String = "",
    val autor: String = "",
    val fecha: String = ""
)

/**
 * 🔹 Obtiene todas las noticias desde Firestore (colección "news")
 * ordenadas por fechaCreacion (de más reciente a más antigua)
 */
suspend fun fetchNoticias(): List<Noticia> {
    val db = FirebaseFirestore.getInstance()
    val listaNoticias = mutableListOf<Noticia>()

    try {
        val snapshot = db.collection("news")
            .orderBy("fechaCreacion", Query.Direction.DESCENDING)
            .get()
            .await()

        for (doc in snapshot.documents) {
            val titulo = doc.getString("titulo") ?: ""
            val descripcion = doc.getString("descripcion") ?: ""
            val cuerpo = doc.getString("cuerpo") ?: ""
            val tipo = doc.getString("tipo") ?: ""
            val autor = doc.getString("autor") ?: ""
            val fechaField = doc.get("fechaCreacion")

            val fechaString = when (fechaField) {
                is Timestamp -> fechaField.toDate().toString()
                is String -> fechaField
                else -> "Sin fecha"
            }

            listaNoticias.add(
                Noticia(
                    id = doc.id,
                    titulo = titulo,
                    descripcion = descripcion,
                    cuerpo = cuerpo,
                    tipo = tipo,
                    autor = autor,
                    fecha = fechaString
                )
            )
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return listaNoticias
}
