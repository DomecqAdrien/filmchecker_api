package com.esgi.filmchecker.controller

import com.esgi.filmchecker.model.APIParserDTO
import com.esgi.filmchecker.model.Actor
import com.esgi.filmchecker.model.Category
import com.esgi.filmchecker.model.Film
import com.esgi.filmchecker.service.FilmService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class FilmController(private val filmService: FilmService) {


    @GetMapping("/films/{page}")
    fun getFilms(@PathVariable page: Int): List<Film> {
        return filmService.getAllFilms(page)
    }

    @GetMapping("/genres")
    fun getGenres(): List<Category> {
        return filmService.getGenres()
    }

    @GetMapping("/film/{id}/")
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
}