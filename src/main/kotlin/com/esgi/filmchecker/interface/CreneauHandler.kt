package com.esgi.filmchecker.`interface`

import com.esgi.filmchecker.model.Creneau
import com.esgi.filmchecker.service.FilmService
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.logging.Handler

abstract class CreneauHandler(private val next: CreneauHandler?) {
    fun handle(creneau: Creneau){
        if(doHandle(creneau)){
            return
        }
        next?.handle(creneau)
    }

    abstract protected fun doHandle(creneau: Creneau): Boolean
}

class DatetimeVerifier(next: CreneauHandler?): CreneauHandler(next){
    override fun doHandle(creneau: Creneau): Boolean {
        val now = LocalDateTime.now()
        val dateOfSession = LocalDateTime.parse(creneau.dateJour, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        println(now)
        println(dateOfSession)
        if(dateOfSession >= now){
            println("la date n'est pas encore passée")
            return false
        }
        println("datetime not verified")
        throw HandlerException("Date is already passed")
        return true
    }
}

class RoomSpaceVerifier(next: CreneauHandler?): CreneauHandler(next){
    override fun doHandle(creneau: Creneau): Boolean {
        val service = FilmService()
        val reservations = service.getReservationsByCreneau(creneau.id)
        val countReservations = reservations.size
        val salle = service.getSalleById(creneau.salleId)
        println(countReservations)
        println(salle)
        if (salle != null) {
            if(countReservations < salle.capacite!!) {
                println("Assez de places")
                return false
            }
        }
        println("roomspace not verified")
        throw HandlerException("No more space left in room")
        return true
    }
}

class HandlerException(message: String): Exception(message)