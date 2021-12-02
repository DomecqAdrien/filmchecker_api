package com.esgi.filmchecker.service

import model.APIParserDTO
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.time.Duration

@Service
class FilmService {

    val restTemplate: RestTemplate = RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(10)).build()

    fun getAllFilms(page: Int): APIParserDTO? {
        val call = restTemplate.getForEntity(
            "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&include_adult=false&include_video=false&language=fr&api_key=1fd5bafb66ba629104f66c9e439f6ab8&page=${page}", APIParserDTO::class.java)
        return call.body
    }
}