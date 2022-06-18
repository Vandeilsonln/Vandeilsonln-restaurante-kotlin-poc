package com.example.restaurante.service

import com.example.restaurante.model.RestaurantLogo

interface FileStorageService {

    fun storeFile(multiPartLogo: RestaurantLogo)
    fun getImageUrl(restaurantName: String): String
}