package com.esgi.filmchecker.model

import com.fasterxml.jackson.annotation.JsonProperty


data class Crew(
    val department: String?=null,
    val gender: Int = 0,
    val id: Int = 0,
    val job: String?=null,
    val name: String?=null,
    @JsonProperty("profile_path") var imageUrl: String? = null
)