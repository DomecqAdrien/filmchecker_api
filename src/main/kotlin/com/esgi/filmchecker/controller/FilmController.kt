package com.esgi.filmchecker.controller

import com.esgi.filmchecker.model.*
import com.esgi.filmchecker.service.FilmService
import org.springframework.web.bind.annotation.*

@RestController
class FilmController(private val filmService: FilmService) {

    @GetMapping("/test")
    fun test(): List<Favori?> {
        return filmService.test()
    }


    @GetMapping("/films/{page}")
    fun getFilms(@PathVariable page: Int): List<Film> {
        return filmService.getAllFilms(page)
    }

    @GetMapping("/genres")
    fun getGenres(): List<Category> {
        return filmService.getGenres()
    }

    @GetMapping("/film/{id}")
    fun getOneFilm(@PathVariable id: Int): Film? {
        return filmService.getOneFilm(id)
    }

    @GetMapping("/search")
    fun searchFilm(@RequestParam query: String): List<Film> {
        return filmService.searchFilm(query)
    }

    @GetMapping("/film/{id}/actors")
    fun getActors(@PathVariable id: Int): APIParserDTO {
        return filmService.getActorsByFilm(id)
    }

    @GetMapping("/film/{id}/note")
    fun rateMovie(@PathVariable id: Int): String? {
        val userId = "ulAiHD2LP40SbnMyuHiT"
        val movieId = 580489
        return filmService.rateMovie(id, userId, movieId)
    }
}