package com.example.restaurante.configurations

import com.amazonaws.auth.BasicAWSCredentials

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AwsConfiguration {

    @Value("\${aws.access.key}")
    private val accessKey: String? = null

    @Value("\${aws.secret.key}")
    private val secretKey: String? = null

    @Bean
    fun s3Client() : AmazonS3 = AmazonS3ClientBuilder
        .standard()
        .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
        .withRegion(Regions.US_EAST_1)
        .build()
}