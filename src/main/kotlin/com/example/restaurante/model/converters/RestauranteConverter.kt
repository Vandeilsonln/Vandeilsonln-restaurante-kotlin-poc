package com.example.restaurante.model.converters

import com.example.restaurante.model.dtos.request.RestaurantDtoRequest
import com.example.restaurante.model.dtos.response.RestaurantDtoResponse
import com.example.restaurante.model.entities.RestaurantEntity
import org.springframework.stereotype.Component

@Component
class RestauranteConverter {

    fun entityToDto (entity: RestaurantEntity): RestaurantDtoResponse {
        return RestaurantDtoResponse(
            id = entity.id!!,
            name = entity.name,
            type = entity.type,
            deliveryFee = entity.deliveryFee,
            deliveryTime = entity.deliveryTime,
            distance = entity.distance,
            image = entity.image)
    }

    fun dtoToEntity (dto: RestaurantDtoRequest): RestaurantEntity {
        return RestaurantEntity(
            name = dto.name,
            type = dto.type,
            deliveryFee = dto.deliveryFee,
            deliveryTime = dto.deliveryTime,
            distance = dto.distance,
            image = null)
    }
}