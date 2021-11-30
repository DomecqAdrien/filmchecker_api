package com.esgi.filmchecker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FilmcheckerApplication

fun main(args: Array<String>) {
	runApplication<FilmcheckerApplication>(*args)
}
