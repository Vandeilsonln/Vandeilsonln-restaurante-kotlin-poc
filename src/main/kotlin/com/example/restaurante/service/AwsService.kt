package com.example.restaurante.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.example.restaurante.model.dtos.RestaurantDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream

@Service
class AwsService (val aws: AmazonS3,
                  @Value("\${aws.bucket.name}") val bucketName: String) {

    fun sendLogoToAws(logo: MultipartFile, restaurantDto: RestaurantDto) {
        val file = buildFile(logo)
        aws.putObject(bucketName, restaurantDto.name, file)
        file.delete()
        makeFilePublic(restaurantDto)
    }

    private fun buildFile(logo: MultipartFile): File {
        val file = File(logo.originalFilename!!)
        FileOutputStream(file).use { outputStream -> outputStream.write(logo.bytes)}
        return file
    }

    private fun makeFilePublic(restaurantDto: RestaurantDto) {
        aws.setObjectAcl(bucketName, restaurantDto.name, CannedAccessControlList.PublicRead)
    }

    fun getObjectUrl(bucketName: String, fileKey: String): String {
        return aws.getUrl(bucketName, fileKey).toString()
    }

}