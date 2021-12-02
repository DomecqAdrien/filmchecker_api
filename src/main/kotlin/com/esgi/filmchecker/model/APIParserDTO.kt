package com.esgi.filmchecker.model

import com.fasterxml.jackson.annotation.JsonProperty

data class APIParserDTO (
    @JsonProperty("total_results") val totalResults: Int?=null,
    @JsonProperty("total_pages") val totalPages: Int?=null,
    @JsonProperty("results") val films: List<Film>?=null,
    @JsonProperty("genres") val categories: List<Category>?=null,
    @JsonProperty("cast") val actors: List<Actor>?=null,
    @JsonProperty("crew") val crew: List<Crew>?=null
)
