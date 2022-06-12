package com.example.restaurante.model.entities

import com.example.restaurante.model.enums.TypeEnum
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "restaurantes")
class RestaurantEntity(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @field:Column(nullable = false)
    var name: String,

    @field:Column(nullable = false)
    @field:Enumerated(EnumType.STRING)
    var tipo: TypeEnum,

    @field:Column(nullable = false, scale = 2, precision = 10)
    var frete: BigDecimal,

    @field:Column(nullable = false)
    var entrega: Int = 0,

    @field:Column(nullable = false)
    var distancia: Double,

    @field:Column()
    var imagem: String = ""
) {
}