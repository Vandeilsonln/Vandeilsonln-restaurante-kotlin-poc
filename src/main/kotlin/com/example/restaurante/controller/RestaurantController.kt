package com.example.restaurante.controller

import com.example.restaurante.model.entities.RestaurantEntity
import com.example.restaurante.repository.RestaurantRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/restaurantes")
class RestaurantController(val repository : RestaurantRepository) {

    @GetMapping
    fun getAllRestaurants(): ResponseEntity<Array<RestaurantEntity>> {
        val restaurants: Array<RestaurantEntity> = repository.findAll().toTypedArray()
        return ResponseEntity.ok(restaurants)
    }
}