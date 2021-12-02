package com.esgi.filmchecker.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Film(
    val id: Int = 0,
    val title: String?=null,
    @JsonProperty("original_title") val titreOriginal: String?=null,
    @JsonProperty("release_date") val date: String?=null,
    val budget :Int = 0,
    @JsonProperty("genre_ids") val genresId: IntArray,
    var genres: MutableList<Category> = mutableListOf(),
    val restriction: String?=null,
    val runtime: Int = 0,
    var revenue: Long = 0,
    var tagline: String?=null,
    val overview: String?=null,
    @JsonProperty("poster_path") val affiche: String?=null,
    val popularity: Double = 0.0,
    @JsonProperty("vote_average") val voteAverage: Double = 0.0
)