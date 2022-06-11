package com.example.restaurante.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.AccessControlList
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.example.restaurante.model.converters.RestauranteConverter
import com.example.restaurante.model.dtos.RestaurantDto
import com.example.restaurante.model.entities.RestaurantEntity
import com.example.restaurante.repository.RestaurantRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream
import java.util.*


@Service
class AwsService(
    val aws: AmazonS3,
    @Value("\${aws.bucket.name}") val bucketName: String,
    val repository: RestaurantRepository,
    val converter: RestauranteConverter
) {

    fun getRestaurantData(id: Long): RestaurantDto {
        var restaurant = repository.findById(id).orElseThrow{RuntimeException("Not Found")}
        return converter.entityToDto(restaurant)
    }

    fun findAllRestaurants(): Array<RestaurantEntity> {
        return repository.findAll().toTypedArray()
    }

    fun register(data: MultipartFile): Unit {
        val file = File(data.originalFilename!!)
        FileOutputStream(file).use { outputStream -> outputStream.write(data.bytes) }
        aws.putObject(bucketName, "image_teste.jpg", file)
        aws.setObjectAcl(bucketName, "image_teste.jpg", CannedAccessControlList.PublicRead)
    }
}
