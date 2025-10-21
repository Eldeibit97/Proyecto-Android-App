package com.example.proyectofinal.modelos

data class PersonalInfo(
    val nombre: String = "Jesús Alberto",
    val nombreChofer: String = "Daniel Gonzalez",
    val apellido: String = "Jiménez Paz",
    val telefono: String = "+52 81 1234 5678",
    val origen: String = "Av. Constitución 123, Centro, Monterrey",
    val destino: String = "Posada del Peregrino, Av. Simón Bolívar 190, Monterrey",
    val fecha: String = "16/Octubre/2025",
    val hora: String = "17:45",
    val personas: Int = 3
)