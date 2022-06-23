package com.example.restaurante.controller

import com.example.restaurante.model.dtos.RestaurantDto
import com.example.restaurante.service.RestaurantService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("v1/restaurants")
class RestaurantController(val service : RestaurantService) {

    @GetMapping
    fun getAllRestaurants(): ResponseEntity<List<RestaurantDto>> {
        return ResponseEntity.ok(service.findAllRestaurants())
    }

    @GetMapping("/{id}")
    fun getRestaurant(@PathVariable id: Long): ResponseEntity<RestaurantDto> {
        return ResponseEntity.ok(service.getRestaurant(id))
    }

    @PostMapping
    fun registerNewRestaurant(registerRestaurantDto: RestaurantDto): ResponseEntity<RestaurantDto> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerNewRestaurant(registerRestaurantDto))
    }

    @PatchMapping("/{id}/logo", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun updateRestaurantLogo(restaurantLogo: MultipartFile, @PathVariable id: Long): ResponseEntity.HeadersBuilder<*> {
        service.updateRestaurantLogo(restaurantLogo, id)
        return ResponseEntity.ok()
    }
}