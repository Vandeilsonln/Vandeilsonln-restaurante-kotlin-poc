package com.example.restaurante.repository

import com.example.restaurante.model.entities.RestaurantEntity
import com.example.restaurante.model.enums.TypeEnum
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RestaurantRepository : JpaRepository<RestaurantEntity, Long> {
    fun findAllByTipo(tipo : TypeEnum) : List<RestaurantEntity>

    @Modifying
    @Query("UPDATE RestaurantEntity set imagem = :logoUrl WHERE id = :id")
    fun updateRestaurantLogo(logoUrl: String, id: Long)
}