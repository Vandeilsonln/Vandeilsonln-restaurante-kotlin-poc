package com.example.restaurante.controller

import com.example.restaurante.model.dtos.RegisterRestaurantRequestDto
import com.example.restaurante.model.dtos.RestaurantDto
import com.example.restaurante.model.entities.RestaurantEntity
import com.example.restaurante.service.RestaurantService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/restaurantes")
class RestaurantController(val service : RestaurantService) {
    @GetMapping
    fun getAllRestaurants(): ResponseEntity<Array<RestaurantEntity>> {
        return ResponseEntity.ok(service.findAllRestaurants())
    }

    @GetMapping("/{id}")
    fun getRestaurant(@PathVariable id: Long): ResponseEntity<RestaurantDto> {
        return ResponseEntity.ok(service.getRestaurantData(id))
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE])
    fun registerNewRestaurant(registerRestaurantRequestDto: RegisterRestaurantRequestDto): ResponseEntity<RestaurantEntity> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerNewRestaurant(registerRestaurantRequestDto))
    }
}