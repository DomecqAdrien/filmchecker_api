package com.esgi.filmchecker.model

import com.fasterxml.jackson.annotation.JsonProperty


data class Actor(
    val name: String,
    var character: String,
    @JsonProperty("profile_path") var imageUrl: String? = null
)