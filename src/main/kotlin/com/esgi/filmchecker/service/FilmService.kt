package com.esgi.filmchecker.service

import com.esgi.filmchecker.model.*
import com.google.firebase.cloud.FirestoreClient
import org.springframework.stereotype.Service

@Service
class FilmService {

    private val apiService: ApiService = ApiService()
    private val apiKey = "1fd5bafb66ba629104f66c9e439f6ab8"
    private val url = "https://api.themoviedb.org/3/"

    fun getAllFilms(page: Int): List<Film> {
        val call = apiService.getCall(
            "${url}discover/movie?sort_by=popularity.desc&include_adult=false&include_video=false&language=fr&api_key=${apiKey}&page=${page}",
            APIParserDTO::class.java
        )
        return call.body?.films?: emptyList()
    }

    fun getGenres(): List<Category> {
        val call = apiService.getCall(
            "${url}genre/movie/list?&language=fr&api_key=${apiKey}",
            APIParserDTO::class.java
        )
        return call.body?.categories?: emptyList()
    }
    fun getOneFilm(filmId: Int) : Film? {
        val call = apiService.getCall(
            "${url}movie/${filmId}?language=fr&api_key=${apiKey}",
            Film::class.java
        )
        return call.body
    }

    fun getActorsByFilm(filmId: Int): APIParserDTO {
        val call = apiService.getCall(
            "${url}movie/${filmId}/credits?&language=fr&api_key=${apiKey}",
            APIParserDTO::class.java
        )
        return call.body!!
    }

    fun searchFilm(query: String): List<Film> {
        val call = apiService.getCall(
            "${url}search/movie?language=fr&query=${query}&api_key=${apiKey}&popularity.desc",
            APIParserDTO::class.java
        )
        return call.body?.films?: emptyList()
    }

    fun test(): List<Favori?> {
        val dbFirestore = FirestoreClient.getFirestore()
        val docs = dbFirestore.collection("favoris")
        return docs.listDocuments().map { it.get().get().toObject(Favori::class.java) }
    }

    fun rateMovie(movieId: Int, userEmail: String, note: Int): String? {
        val note = Note(userEmail, movieId, note)
        println(note)
        val dbFirestore = FirestoreClient.getFirestore()
        val collectionsApiFuture = dbFirestore.collection("notes").document().set(note)
        return collectionsApiFuture.get().updateTime.toString()
    }

    fun commentMovie(movieId: Int, userEmail: String, comment: String): String? {
        val comment = Comment(userEmail, movieId, comment)
        println(comment)
        val dbFirestore = FirestoreClient.getFirestore()
        val collectionsApiFuture = dbFirestore.collection("comments").document().set(comment)
        return collectionsApiFuture.get().updateTime.toString()
    }


}