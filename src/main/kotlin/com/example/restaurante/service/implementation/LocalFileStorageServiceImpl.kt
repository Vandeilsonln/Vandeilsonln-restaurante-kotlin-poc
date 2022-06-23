package com.example.restaurante.service.implementation

import com.example.restaurante.service.FileStorageService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import org.springframework.util.FileCopyUtils
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path

@Service
@Profile("development")
class LocalFileStorageServiceImpl (@Value("\${application.storage.local.path}") val directoryPath: Path): FileStorageService {

    override fun storeFile(restaurantLogo: MultipartFile, restaurantName: String) {
        val filePath: Path = getFilePath(restaurantName)
        FileCopyUtils.copy(restaurantLogo.inputStream, Files.newOutputStream(filePath))
    }

    private fun getFilePath(restaurantName: String) = directoryPath.resolve(Path.of(restaurantName))

    override fun getImageUrl(restaurantName: String): String {
        return "${directoryPath}/${restaurantName}"
    }
}