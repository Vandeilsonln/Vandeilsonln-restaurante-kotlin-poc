package com.example.restaurante.model.converters

import com.example.restaurante.model.dtos.RestaurantDto
import com.example.restaurante.model.entities.RestaurantEntity
import org.springframework.stereotype.Component

@Component
class RestauranteConverter {

    fun entityToDto (entity: RestaurantEntity): RestaurantDto {
        return RestaurantDto(
            name = entity.name,
            tipo = entity.tipo,
            frete = entity.frete,
            entrega = entity.entrega,
            distancia = entity.distancia,
            imagem = entity.imagem)
    }

    fun dtoToEntity (dto: RestaurantDto): RestaurantEntity {
        return RestaurantEntity(
            name = dto.name,
            tipo = dto.tipo,
            frete = dto.frete,
            entrega = dto.entrega,
            distancia = dto.distancia,
            imagem = dto.imagem!!)
    }
}