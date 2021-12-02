package com.esgi.filmchecker.model

import com.fasterxml.jackson.annotation.JsonProperty
import model.Actor
import model.Category
import model.Crew
import model.Film

data class APIParserDTO (
    val total_results: Int?=null,
    val totalPages: Int?=null,
    @JsonProperty("results") val films: List<Film>?=null,
    val categories: List<Category>?=null,
    val actors: List<Actor>?=null,
    val crew: List<Crew>?=null
)
