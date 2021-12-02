package com.esgi.filmchecker.service

import com.esgi.filmchecker.model.APIParserDTO
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import java.time.Duration

class ApiService {

    private val restTemplate: RestTemplate = RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(10)).build()

    fun call(url: String): ResponseEntity<APIParserDTO> {
        return restTemplate.getForEntity(url, APIParserDTO::class.java)
    }


}