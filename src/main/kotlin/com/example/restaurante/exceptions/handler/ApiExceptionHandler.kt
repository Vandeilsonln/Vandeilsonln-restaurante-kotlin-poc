package com.example.restaurante.exceptions.handler

import com.example.restaurante.exceptions.RestaurantNotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception

@ControllerAdvice
class ApiExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(RestaurantNotFoundException::class)
    fun handleAnyException(ex: RestaurantNotFoundException, webRequest: WebRequest): ResponseEntity<Any> {
        return handleExceptionInternal(ex, ex.message, HttpHeaders(), HttpStatus.NOT_FOUND, webRequest)
    }

    override fun handleExceptionInternal(
        ex: Exception,
        body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val message = body ?: ex.message
        val errorModelBody = ErrorMessageModel(message as String?)
        return super.handleExceptionInternal(ex, ErrorMessageModel(message as String?), headers, status, request)
    }
}