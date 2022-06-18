package com.example.restaurante.model

import org.springframework.web.multipart.MultipartFile

data class RestaurantLogo(
    var logoData: MultipartFile,
    var logoName: String
)