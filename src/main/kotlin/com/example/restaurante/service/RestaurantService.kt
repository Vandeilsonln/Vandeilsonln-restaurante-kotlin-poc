package com.example.restaurante.service

import com.example.restaurante.model.converters.RestauranteConverter
import com.example.restaurante.model.dtos.RestaurantDto
import com.example.restaurante.model.entities.RestaurantEntity
import com.example.restaurante.repository.RestaurantRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class RestaurantService(
    val awsService: AwsService,
    @Value("\${aws.bucket.name}") val bucketName: String,
    val repository: RestaurantRepository,
    val converter: RestauranteConverter
) {

    fun getRestaurantData(id: Long): RestaurantDto {
        val restaurant = repository.findById(id).orElseThrow{RuntimeException("Not Found")}
        return converter.entityToDto(restaurant)
    }

    fun findAllRestaurants(): Array<RestaurantEntity> {
        return repository.findAll().toTypedArray()
    }

    fun registerNewRestaurant(restaurantData: String, logo: MultipartFile): RestaurantEntity {
        val restaurantDto: RestaurantDto = convertJsonToRestaurantDto(restaurantData)
        awsService.sendLogoToAws(logo, restaurantDto)
        restaurantDto.imagem = awsService.getObjectUrl(bucketName, restaurantDto.name)
        return repository.save(converter.dtoToEntity(restaurantDto))
    }

    fun convertJsonToRestaurantDto(restaurantJson: String): RestaurantDto {
        val mapper = jacksonObjectMapper()
        return mapper.readValue(restaurantJson)
    }
}
