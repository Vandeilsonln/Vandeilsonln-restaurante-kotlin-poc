package com.example.restaurante.repository

import com.example.restaurante.model.entities.RestaurantEntity
import com.example.restaurante.model.enums.TypeEnum
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RestaurantRepository : JpaRepository<RestaurantEntity, Long> {
    fun findAllByTipo(tipo : TypeEnum) : List<RestaurantEntity>
}