package com.example.restaurante.model.dtos

import org.springframework.web.multipart.MultipartFile

data class RegisterRestaurantRequestDto(
    var logo: MultipartFile,
    var restaurantData: String
)