package com.example.restaurante.service

import org.springframework.web.multipart.MultipartFile

interface FileStorageService {

    fun storeFile(multiPartLogo: MultipartFile, restaurantName: String)
    fun getImageUrl(restaurantName: String): String
}