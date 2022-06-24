package com.example.restaurante.model.dtos.request

import com.example.restaurante.model.enums.TypeEnum
import java.math.BigDecimal

data class RestaurantDtoRequest(
    var name: String,
    var type: TypeEnum,
    var deliveryFee: BigDecimal,
    var deliveryTime: Int = 0,
    var distance: Double
    )
