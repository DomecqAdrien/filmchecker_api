package model

data class Film(
    val id: Int = 0,
    val title: String?=null,
    val titreOriginal: String?=null,
    val date: String?=null,
    val budget :Int = 0,
    val genre_ids: IntArray,
    var genres: MutableList<Category> = mutableListOf(),
    val restriction: String?=null,
    val runtime: Int = 0,
    var revenue: Long = 0,
    var tagline: String?=null,
    val overview: String?=null,
    val affiche: String?=null,
    val popularity: Double = 0.0,
    val voteAverage: Double = 0.0
)