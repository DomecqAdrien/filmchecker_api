package model

data class APIParserDTO (
    val total_results: Int?=null,
    val totalPages: Int?=null,
    val results: List<Film>?=null,
    val categories: List<Category>?=null,
    val actors: List<Actor>?=null,
    val crew: List<Crew>?=null
)
