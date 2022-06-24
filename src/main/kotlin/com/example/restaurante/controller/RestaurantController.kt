package com.example.restaurante.controller

import com.example.restaurante.model.dtos.request.RestaurantDtoRequest
import com.example.restaurante.model.dtos.response.RestaurantDtoResponse
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
    fun getAllRestaurants(): ResponseEntity<List<RestaurantDtoResponse>> {
        return ResponseEntity.ok(service.findAllRestaurants())
    }

    @GetMapping("/{id}")
    fun getRestaurant(@PathVariable id: Long): ResponseEntity<RestaurantDtoResponse> {
        return ResponseEntity.ok(service.getRestaurant(id))
    }

    @PostMapping
    fun registerNewRestaurant(@RequestBody registerRestaurantDto: RestaurantDtoRequest): ResponseEntity<RestaurantDtoResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerNewRestaurant(registerRestaurantDto))
    }

    @PatchMapping("/{id}/logo", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun updateRestaurantLogo(@RequestParam("logo") restaurantLogo: MultipartFile, @PathVariable id: Long) {
        service.updateRestaurantLogo(restaurantLogo, id)
    }
}