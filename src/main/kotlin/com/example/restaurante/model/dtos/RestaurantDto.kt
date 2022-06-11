package com.example.restaurante.model.dtos

import com.example.restaurante.model.enums.TypeEnum
import java.math.BigDecimal

data class RestaurantDto(
    val id: Long,
    val name: String,
    val tipo: TypeEnum,
    val frete: BigDecimal,
    val entrega: Int = 0,
    val distancia: Double,
    val imagem: String = ""
)
