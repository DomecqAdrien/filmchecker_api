package com.esgi.filmchecker.controller

import com.esgi.filmchecker.service.FilmService
import model.Film
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class FilmController(private val filmService: FilmService) {


    @GetMapping("/films/{page}")
    fun getFilms(@PathVariable page: Int): List<Film> {
        return filmService.getAllFilms(page)
    }

    @GetMapping("/genres")
    fun getGenres() {

    }

    @GetMapping("movie/{id}/{language}")
    fun getOneFilm(@PathVariable id: Int, @PathVariable language: String) {

    }

    @GetMapping("movie/{id}")
    fun getActorsByFilm(@PathVariable id: Int) {

    }

    @GetMapping("/search/{language}/{query}")
    fun searchFilm(@PathVariable language: String, @PathVariable query: String) {

    }



}