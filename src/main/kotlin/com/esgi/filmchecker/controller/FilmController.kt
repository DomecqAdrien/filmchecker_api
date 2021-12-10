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

    @GetMapping("/film/{movieId}/user/{userEmail}/note/{note}")
    fun rateMovie(@PathVariable movieId: Int, @PathVariable userEmail: String, @PathVariable note: Int): String? {
        return filmService.rateMovie(movieId, userEmail, note)
    }


    @PostMapping("/film/{movieId}/user/{userEmail}/comment")
    fun commentMovie(@PathVariable movieId: Int, @PathVariable userEmail: String,@RequestBody comment: Comment): String? {
        return filmService.commentMovie(movieId, userEmail, comment)
    }

    @GetMapping("/user/{userEmail}/movie/{movieId}/favori")
    fun handleFavorites(@PathVariable userEmail: String, @PathVariable movieId: Int): String{
        return filmService.handleFavorites(userEmail, movieId)
    }

    @GetMapping("/create-sessions")
    fun createSessions(): String{
        return filmService.createSessions();
    }

    @GetMapping("/film/{movieId}/creneaux")
    fun getCreneauxByMovie(@PathVariable movieId: Int): List<Creneau> {
        return filmService.getCreneauxByMovie(movieId);
    }

    @GetMapping("/film/{movieId}/notes")
    fun getNotesByMovie(@PathVariable movieId: Int): List<Note> {
        return filmService.getNotesByMovie(movieId);
    }

    @GetMapping("/film/{movieId}/comments")
    fun getCommentsByMovie(@PathVariable movieId: Int): List<Comment> {
        return filmService.getCommentsByMovie(movieId);
    }

    @PostMapping("/book-session/")
    fun getCommentsByMovie(@RequestBody reservation: Reservation): String {
        return filmService.bookSession(reservation);
    }
}