package com.esgi.filmchecker.service

import com.esgi.filmchecker.model.Actor
import com.esgi.filmchecker.model.Category
import com.esgi.filmchecker.model.Film
import org.springframework.stereotype.Service

@Service
class FilmService {

    private val apiService: ApiService = ApiService()
    private val apiKey = "1fd5bafb66ba629104f66c9e439f6ab8"
    private val url = "https://api.themoviedb.org/3/"

    fun getAllFilms(page: Int): List<Film> {
        val call = apiService.call(
            "${url}discover/movie?sort_by=popularity.desc&include_adult=false&include_video=false&language=fr&api_key=${apiKey}&page=${page}"
        )
        return call.body?.films?: emptyList()
    }

    fun getGenres(): List<Category> {
        val call = apiService.call(
            "${url}genre/movie/list?&language=fr"
        )
        return call.body?.categories?: emptyList()
    }

    fun getActorsByFilm(filmId: Int): List<Actor> {
        val call = apiService.call(
            "${url}movie/${filmId}/credits?&language=fr"
        )
        return call.body?.actors?: emptyList()
    }

    fun searchFilm(query: String): List<Film> {
        val call = apiService.call(
            "${url}search/movie/list?&language=fr&query=${query}"
        )
        return call.body?.films?: emptyList()
    }








}