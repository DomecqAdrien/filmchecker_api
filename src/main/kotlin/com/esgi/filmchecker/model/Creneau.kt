package com.esgi.filmchecker.model


data class Creneau(
    val heureDebut: String? = null,
    val heureFin: String? = null,
    val movieId: Int? = null,
    val salleId: Int? = null,
    val dateJour: String? = null
)