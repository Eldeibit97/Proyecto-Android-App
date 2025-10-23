package com.example.proyectofinal.modelos

data class PersonalInfo(
    val nombre: String = "Juan",
    val nombreChofer: String = "Josue Tijerina",
    val apellido: String = "Perez",
    val telefono: String = "+52 81 1384 4318",
    val origen: String = "Tecnologico de Monterrey",
    val destino: String = "Posada del Peregrino",
    val fecha: String = "23/Octubre/2025",
    val hora: String = "19:00",
    val personas: Int = 3
)