package com.esgi.filmchecker.service

import com.esgi.filmchecker.model.APIParserDTO
import model.Film
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.time.Duration

@Service
class FilmService {

    private val apiService: ApiService = ApiService()

    fun getAllFilms(page: Int): List<Film> {
        val call = apiService.call(
            "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&include_adult=false&include_video=false&language=fr&api_key=1fd5bafb66ba629104f66c9e439f6ab8&page=${page}"
        )
        return call.body?.films?: emptyList()
    }


}