package com.example.restaurante.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.view.InternalResourceViewResolver
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SwaggerConfiguration {

    @Bean
    fun defaultViewResolver(): InternalResourceViewResolver? {
        return InternalResourceViewResolver()
    }

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(getApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.restaurante.controller"))
            .paths(PathSelectors.any())
            .build();
    }

    private fun getApiInfo(): ApiInfo {
        val contact = Contact("Vandeilson Nobre", "https://www.linkedin.com/in/vandeilson-nobre", "vandernobrel@gmail.com")
        return ApiInfoBuilder()
            .title("Restaurant API")
            .description("Example API using AWS S3, Heroku and Kotlin")
            .version("1.0.0")
            .contact(contact)
            .build()
    }
}