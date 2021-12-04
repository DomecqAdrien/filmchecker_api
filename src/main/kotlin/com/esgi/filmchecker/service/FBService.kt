package com.esgi.filmchecker.service

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.nio.file.Paths
import javax.annotation.PostConstruct

@Service
class FBService {

    @PostConstruct
    fun initialize() {
        try {
            //val res = javaClass.classLoader.getResourceAsStream ("serviceaccount.json")!!
            //val file = Paths.get(res.toURI()).toFile()
            //val absolutePath: String = file.absolutePath
            val serviceAccount = javaClass.classLoader.getResourceAsStream ("serviceaccount.json")!!
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