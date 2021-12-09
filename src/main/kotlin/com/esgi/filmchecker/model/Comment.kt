package com.esgi.filmchecker.model

data class Comment(
    val userEmail: String?= null,
    val movieId: Int?= null,
    val comment: String?= null
)