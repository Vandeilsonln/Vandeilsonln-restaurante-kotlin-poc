package com.example.restaurante.exceptions.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler
    fun handleAnyException(ex: Exception): ResponseEntity<ErrorMessageModel> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessageModel("Socorro"))
    }
}