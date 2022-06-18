package com.example.restaurante.service.implementation

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.example.restaurante.service.FileStorageService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileOutputStream

@Service
class S3FileStorageServiceImpl (val aws: AmazonS3,
                                @Value("\${aws.bucket.name}") val bucketName: String): FileStorageService {

    override fun storeFile(multiPartLogo: MultipartFile, restaurantName: String) {
        val logoFile = buildFile(multiPartLogo)
        aws.putObject(bucketName, restaurantName, logoFile)
        logoFile.delete()
        makeFilePublic(restaurantName)
    }

    private fun buildFile(logo: MultipartFile): File {
        val file = File(logo.originalFilename!!)
        FileOutputStream(file).use { outputStream -> outputStream.write(logo.bytes)}
        return file
    }

    private fun makeFilePublic(restaurantName: String) {
        aws.setObjectAcl(bucketName, restaurantName, CannedAccessControlList.PublicRead)
    }

    override fun getImageUrl(restaurantName: String): String {
        return aws.getUrl(bucketName, restaurantName).toString()
    }
}