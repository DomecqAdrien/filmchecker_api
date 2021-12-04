package com.esgi.filmchecker.service

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class FBService {

    @PostConstruct
    fun initialize() {
        try {
            val serviceAccount = javaClass.classLoader.getResourceAsStream ("serviceaccount.json")
            val options: FirebaseOptions = FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://filmchecker-d3c80.firebaseio.com/")
                .build()
            FirebaseApp.initializeApp(options)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}