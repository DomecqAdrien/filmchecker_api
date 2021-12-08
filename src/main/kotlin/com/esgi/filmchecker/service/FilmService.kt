package com.esgi.filmchecker.service

import com.esgi.filmchecker.model.*
import com.google.firebase.cloud.FirestoreClient
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
        docs.listDocuments().forEach {
            println(it.id)

        }
        return docs.listDocuments().map { it.get().get().toObject(Favori::class.java) }
    }

    fun rateMovie(movieId: Int, userEmail: String, note: Int): String? {
        val note = Note(userEmail, movieId, note)
        println(note)
        val dbFirestore = FirestoreClient.getFirestore()
        val collectionsApiFuture = dbFirestore.collection("notes").document().set(note)
        return collectionsApiFuture.get().updateTime.toString()
    }

    fun commentMovie(movieId: Int, userEmail: String, comment: Comment): String? {
        val comment = Comment(userEmail, movieId, comment.comment)
        println(comment)
        val dbFirestore = FirestoreClient.getFirestore()
        val collectionsApiFuture = dbFirestore.collection("comments").document().set(comment)
        return collectionsApiFuture.get().updateTime.toString()
    }

    fun handleFavorites(userEmail: String, movieId: Int): String {
        val dbFirestore = FirestoreClient.getFirestore()
        val docs = dbFirestore.collection("favoris")
        for(doc in docs.listDocuments()){
            val favori = doc.get().get().toObject(Favori::class.java)
            val id = doc.get().get().id
            if(favori?.movieId == movieId && favori?.userEmail == userEmail){
                val collectionsApiFuture = dbFirestore.collection("favoris").document(id).delete()
                return collectionsApiFuture.get().updateTime.toString()
            }
            else{
                val favori = Favori(true,movieId, userEmail)
                val collectionsApiFuture = dbFirestore.collection("favoris").document().set(favori)
                return collectionsApiFuture.get().updateTime.toString()
            }
        }
        return "ok"
    }

    fun getCreneauxByMovie(movieId: Int): List<Creneau> {
        val dbFirestore = FirestoreClient.getFirestore()
        val docs = dbFirestore.collection("creneaux")
        var creneauxByMovie = mutableListOf<Creneau>()
        for(doc in docs.listDocuments()){
            val creneau = doc.get().get().toObject(Creneau::class.java)
            if(creneau?.movieId == movieId){
                creneauxByMovie.add(creneau)
            }
        }
        return creneauxByMovie
    }

    fun getNotesByMovie(movieId: Int): List<Note> {
        val dbFirestore = FirestoreClient.getFirestore()
        val docs = dbFirestore.collection("notes")
        var notesByMovie = mutableListOf<Note>()
        for(doc in docs.listDocuments()){
            val note = doc.get().get().toObject(Note::class.java)
            if(note?.movieId == movieId){
                notesByMovie.add(note)
            }
        }
        return notesByMovie
    }

    fun createCreneau(creneau: Creneau): String{
        val dbFirestore = FirestoreClient.getFirestore()
        val collectionsApiFuture = dbFirestore.collection("creneaux").document().set(creneau)
        return collectionsApiFuture.get().updateTime.toString()
    }

    fun getSalles(): List<Salle?> {
        val dbFirestore = FirestoreClient.getFirestore()
        val docs = dbFirestore.collection("salles")
        return docs.listDocuments().map { it.get().get().toObject(Salle::class.java) }
    }

    fun createSessions() : String{
        val horairesDebut = listOf<String>("00h00","3h00", "9h00", "13h00", "16h00", "19h00", "22h00")
        val horairesFin = listOf<String>("02h00","5h00", "12h00", "15h00", "18h00", "21h00", "00h00")
        val currentDate = LocalDateTime.now();
        var currentYear = currentDate.year
        var currentMonth = currentDate.monthValue
        var currentDay = currentDate.dayOfMonth
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        var formattedDate = currentDate.format(formatter);
        var films = listOf<Film>()
        films = getAllFilms(1)
        val moviesIterator = films.listIterator()
        var salles = listOf<Salle?>()
        salles = getSalles()

        while(moviesIterator.hasNext()){
            var movie = moviesIterator.next()
            var sallesIterator = salles.listIterator()
            while(sallesIterator.hasNext()) {
                var salle = sallesIterator.next()
                val beginningIterator = horairesDebut.listIterator()
                val endingIterator = horairesFin.listIterator()
                while (beginningIterator.hasNext()) {
                    var creneau = Creneau(beginningIterator.next(), endingIterator.next(), movie.id, salle?.numeroSalle, formattedDate)
                    println(creneau)
                    //TODO("Change with real create")
                }
            }
            currentDay += 1
            var dateOfSession = LocalDateTime.of(currentYear, currentMonth, currentDay, 0, 0)
            formattedDate = dateOfSession.format(formatter);
        }
        return "ok"
    }
}