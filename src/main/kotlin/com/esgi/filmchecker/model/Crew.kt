package model

data class Crew(
    val department: String?=null,
    val gender: Int = 0,
    val id: Int = 0,
    val job: String?=null,
    val name: String?=null,
    var imageUrl: String? = null
)