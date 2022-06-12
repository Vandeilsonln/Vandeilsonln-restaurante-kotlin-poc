package com.example.restaurante.model.dtos

import com.example.restaurante.model.enums.TypeEnum
import java.math.BigDecimal

data class RestaurantDto(
    var name: String,
    var tipo: TypeEnum,
    var frete: BigDecimal,
    var entrega: Int = 0,
    var distancia: Double,
    var imagem: String? = null
)
