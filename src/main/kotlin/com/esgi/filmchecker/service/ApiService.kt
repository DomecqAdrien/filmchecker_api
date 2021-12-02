package com.esgi.filmchecker.service

import com.esgi.filmchecker.model.APIParserDTO
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import java.time.Duration
import kotlin.reflect.KClass

class ApiService {

    private val restTemplate: RestTemplate = RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(10)).build()

    fun <T: Any> getCall(url: String, type: Class<T>): ResponseEntity<T> {
        return restTemplate.getForEntity(url, type)
    }


}