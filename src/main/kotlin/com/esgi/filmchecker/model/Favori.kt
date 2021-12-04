package com.esgi.filmchecker.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Favori(
    val favori: Boolean? = null,
    val movieId: Int? = null,
    val userEmail: String? = null
)