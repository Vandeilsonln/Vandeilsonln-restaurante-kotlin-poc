package com.example.restaurante.service.implementation

import com.example.restaurante.model.RestaurantLogo
import com.example.restaurante.service.FileStorageService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.util.FileCopyUtils
import java.nio.file.Files
import java.nio.file.Path

@Service
class LocalFileStorageServiceImpl (@Value("\${application.storage.local.path}") val directoryPath: Path): FileStorageService {

    override fun storeFile(restaurantLogo: RestaurantLogo) {
        val filePath: Path = getFilePath(restaurantLogo.logoName)
        FileCopyUtils.copy(restaurantLogo.logoData.inputStream, Files.newOutputStream(filePath))
    }

    private fun getFilePath(restaurantName: String) = directoryPath.resolve(Path.of(restaurantName))

    override fun getImageUrl(restaurantName: String): String {
        return "${directoryPath}/${restaurantName}"
    }
}