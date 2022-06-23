package com.example.restaurante.service

import org.springframework.web.multipart.MultipartFile

interface FileStorageService {

    fun storeFile(restaurantLogo: MultipartFile, restaurantName: String)
    fun getImageUrl(restaurantName: String): String
}