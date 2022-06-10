package com.example.restaurante.configurations

import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.URI
import java.net.URISyntaxException

//@Configuration
class DatabaseConfiguration {

    @Bean
    @Throws(URISyntaxException::class)
    fun dataSource(): BasicDataSource {
        val dbUri = URI(System.getenv("DATABASE_URL"))
        val username = dbUri.userInfo.split(":").toTypedArray()[0]
        val password = dbUri.userInfo.split(":").toTypedArray()[1]
        val dbUrl = "jdbc:postgresql://" + dbUri.host + ':' + dbUri.port + dbUri.path + "?sslmode=require"

        val basicDataSource = BasicDataSource()
        basicDataSource.url = dbUrl
        basicDataSource.username = username
        basicDataSource.password = password
        return basicDataSource
    }
}