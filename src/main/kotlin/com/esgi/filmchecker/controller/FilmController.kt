package com.esgi.filmchecker.controller

import com.esgi.filmchecker.service.FilmService
import model.APIParserDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class FilmController(val filmService: FilmService) {


    @GetMapping("/films/{page}")
    fun getFilms(@PathVariable page: Int): APIParserDTO? {
        return filmService.getAllFilms(page)
    }

}