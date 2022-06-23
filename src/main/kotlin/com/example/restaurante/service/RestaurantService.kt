package com.example.restaurante.service

import com.example.restaurante.exceptions.RestaurantNotFoundException
import com.example.restaurante.model.converters.RestauranteConverter
import com.example.restaurante.model.dtos.RestaurantDto
import com.example.restaurante.repository.RestaurantRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import kotlin.streams.toList

@Service
class RestaurantService(
    val fileStorageService: FileStorageService,
    val repository: RestaurantRepository,
    val converter: RestauranteConverter
) {

    fun getRestaurant(id: Long): RestaurantDto {
        val restaurant = repository.findById(id).orElseThrow{ RestaurantNotFoundException("Restaurant with id $id not found") }
        return converter.entityToDto(restaurant)
    }

    fun findAllRestaurants(): List<RestaurantDto> {
        return repository.findAll().stream()
            .map { i -> converter.entityToDto(i) }
            .toList()
    }

    fun registerNewRestaurant(restaurantDto: RestaurantDto): RestaurantDto {
        val savedRestaurantEntity = repository.save(converter.dtoToEntity(restaurantDto))
        return converter.entityToDto(savedRestaurantEntity)
    }

    fun updateRestaurantLogo(restaurantLogo: MultipartFile, id: Long) {
        val restaurantEntity = repository.findById(id).get()
        fileStorageService.storeFile(restaurantLogo, restaurantEntity.name)
        repository.updateRestaurantLogo(fileStorageService.getImageUrl(restaurantEntity.name), id)
    }
}
