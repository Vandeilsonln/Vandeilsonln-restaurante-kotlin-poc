package com.example.restaurante.model.dtos.response

import com.example.restaurante.model.enums.TypeEnum
import java.math.BigDecimal

data class RestaurantDtoResponse(
    var id: Long,
    var name: String,
    var type: TypeEnum,
    var deliveryFee: BigDecimal,
    var deliveryTime: Int = 0,
    var distance: Double,
    var image: String? = null
)
