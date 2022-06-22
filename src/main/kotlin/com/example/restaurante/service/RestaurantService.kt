package com.example.restaurante.service

import com.example.restaurante.exceptions.RestaurantNotFoundException
import com.example.restaurante.model.RestaurantLogo
import com.example.restaurante.model.converters.RestauranteConverter
import com.example.restaurante.model.dtos.RegisterRestaurantRequestDto
import com.example.restaurante.model.dtos.RestaurantDto
import com.example.restaurante.model.entities.RestaurantEntity
import com.example.restaurante.repository.RestaurantRepository
import org.springframework.stereotype.Service
import kotlin.streams.toList

@Service
class RestaurantService(
    val fileStorageService: FileStorageService,
    val repository: RestaurantRepository,
    val converter: RestauranteConverter
) {

    fun getRestaurantData(id: Long): RestaurantDto {
        val restaurant = repository.findById(id).orElseThrow{ RestaurantNotFoundException("Restaurant with id $id not found") }
        return converter.entityToDto(restaurant)
    }

    fun findAllRestaurants(): List<RestaurantDto> {
        return repository.findAll().stream()
            .map { i -> converter.entityToDto(i) }
            .toList()
    }

    fun registerNewRestaurant(registerRestaurantRequestDto: RegisterRestaurantRequestDto): RestaurantDto {
        val restaurantDto: RestaurantDto = converter.jsonToDto(registerRestaurantRequestDto.restaurantData)
        fileStorageService.storeFile(RestaurantLogo(registerRestaurantRequestDto.logo, restaurantDto.name))
        restaurantDto.imagem = fileStorageService.getImageUrl(restaurantDto.name)
        val savedRestaurantEntity = repository.save(converter.dtoToEntity(restaurantDto))
        return converter.entityToDto(savedRestaurantEntity)
    }

}
