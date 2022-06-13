package com.example.restaurante.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.example.restaurante.model.converters.RestauranteConverter
import com.example.restaurante.model.dtos.RestaurantDto
import com.example.restaurante.model.entities.RestaurantEntity
import com.example.restaurante.repository.RestaurantRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream

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

    fun registerNewRestaurant(restaurantData: String, logo: MultipartFile): RestaurantEntity {
        val restaurantDto: RestaurantDto = convertJsonToRestaurantDto(restaurantData)
        sendLogoToAws(logo, restaurantDto)
        restaurantDto.imagem = aws.getUrl(bucketName, restaurantDto.name).toString()
        return repository.save(converter.dtoToEntity(restaurantDto))
    }

    fun convertJsonToRestaurantDto(restaurantJson: String): RestaurantDto {
        val mapper = jacksonObjectMapper()
        return mapper.readValue(restaurantJson)
    }

    private fun sendLogoToAws(logo: MultipartFile, restaurantDto: RestaurantDto) {
        val file = buildFile(logo)
        aws.putObject(bucketName, restaurantDto.name, file)
        makeFilePublic(restaurantDto)
    }

    fun buildFile(logo: MultipartFile): File {
        val file = File(logo.originalFilename!!)
        FileOutputStream(file).use { outputStream -> outputStream.write(logo.bytes)}
        return file
        }

    private fun makeFilePublic(restaurantDto: RestaurantDto) {
        aws.setObjectAcl(bucketName, restaurantDto.name, CannedAccessControlList.PublicRead)
    }
}
