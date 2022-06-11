package com.example.restaurante.controller

import com.example.restaurante.model.dtos.RestaurantDto
import com.example.restaurante.model.entities.RestaurantEntity
import com.example.restaurante.service.AwsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("v1/restaurantes")
class RestaurantController(val service : AwsService) {
    @GetMapping
    fun getAllRestaurants(): ResponseEntity<Array<RestaurantEntity>> {
        return ResponseEntity.ok(service.findAllRestaurants())
    }

    @GetMapping("/{id}")
    fun getRestaurant(@PathVariable id: Long): ResponseEntity<RestaurantDto> {
        return ResponseEntity.ok(service.getRestaurantData(id))
    }

    @PostMapping
    fun registerNewRestaurant(@RequestParam("logo") data: MultipartFile): Unit {
        return service.register(data)
    }
}