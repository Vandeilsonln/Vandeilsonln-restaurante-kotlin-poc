package com.example.restaurante.service

import com.amazonaws.services.s3.AmazonS3
import com.example.restaurante.model.entities.RestaurantEntity
import com.example.restaurante.repository.RestaurantRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class AwsService(
    val aws: AmazonS3,
    @Value("\${aws.bucket.name}") val bucketName: String,
    val repository: RestaurantRepository) {

    fun getRestaurantData(id: String): String {
        return aws.getUrl(bucketName, id).toString()
    }

    fun findAllRestaurants(): Array<RestaurantEntity> {
        return repository.findAll().toTypedArray()
    }
}
