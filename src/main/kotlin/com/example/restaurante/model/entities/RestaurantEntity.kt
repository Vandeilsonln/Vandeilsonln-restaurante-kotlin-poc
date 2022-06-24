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
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "restaurantes")
class RestaurantEntity(

    @field:Id
    @field:SequenceGenerator(name="pk_sequence", sequenceName = "restaurantes_id_seq", allocationSize = 1, initialValue = 100)
    @field:GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    var id: Long? = null,

    @field:Column(nullable = false, name = "name")
    var name: String,

    @field:Column(nullable = false, name = "tipo")
    @field:Enumerated(EnumType.STRING)
    var type: TypeEnum,

    @field:Column(nullable = false, scale = 2, precision = 10, name = "frete")
    var deliveryFee: BigDecimal,

    @field:Column(nullable = false, name = "entrega")
    var deliveryTime: Int = 0,

    @field:Column(nullable = false, name = "distancia")
    var distance: Double,

    @field:Column(name = "imagem")
    var image: String? = ""
) {
}