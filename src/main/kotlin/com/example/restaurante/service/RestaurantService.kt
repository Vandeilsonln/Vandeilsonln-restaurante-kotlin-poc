package com.example.restaurante.service

import com.example.restaurante.exceptions.handler.RestaurantNotFoundException
import com.example.restaurante.model.RestaurantLogo
import com.example.restaurante.model.converters.RestauranteConverter
import com.example.restaurante.model.dtos.RegisterRestaurantRequestDto
import com.example.restaurante.model.dtos.RestaurantDto
import com.example.restaurante.model.entities.RestaurantEntity
import com.example.restaurante.repository.RestaurantRepository
import org.springframework.stereotype.Service

@Service
class RestaurantService(
    val fileStorageService: FileStorageService,
    val repository: RestaurantRepository,
    val converter: RestauranteConverter
) {

    fun getRestaurantData(id: Long): RestaurantDto {
        val restaurant = repository.findById(id).orElseThrow{RestaurantNotFoundException("Not Found")}
        return converter.entityToDto(restaurant)
    }

    fun findAllRestaurants(): Array<RestaurantEntity> {
        return repository.findAll().toTypedArray()
    }

    fun registerNewRestaurant(registerRestaurantRequestDto: RegisterRestaurantRequestDto): RestaurantEntity { // RestaurantDTO
        val restaurantDto: RestaurantDto = converter.jsonToDto(registerRestaurantRequestDto.restaurantData)
        fileStorageService.storeFile(RestaurantLogo(registerRestaurantRequestDto.logo, restaurantDto.name))
        restaurantDto.imagem = fileStorageService.getImageUrl(restaurantDto.name)
        return repository.save(converter.dtoToEntity(restaurantDto))
    }

}
