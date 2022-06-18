package com.example.restaurante.model.dtos

import org.springframework.web.multipart.MultipartFile

data class RegisterRestaurantRequestDto(
    var multiPartLogo: MultipartFile,
    var restaurantDataJson: String
)