package com.example.restaurante

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class RestauranteApplication

fun main(args: Array<String>) {
	runApplication<RestauranteApplication>(*args)
}
