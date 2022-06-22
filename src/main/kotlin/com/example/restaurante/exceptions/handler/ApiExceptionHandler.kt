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
    fun handleNotFoundException(ex: RestaurantNotFoundException, webRequest: WebRequest): ResponseEntity<Any> {
        val status = HttpStatus.NOT_FOUND
        val body = ErrorMessageModel(
            status = status.value(),
            type = "entity-not-found",
            title = "Restaurant not found",
            detail = ex.message
        )
        return handleExceptionInternal(ex, body, HttpHeaders(), status, webRequest)
    }

    override fun handleExceptionInternal(
        ex: Exception,
        body: Any?,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {

        val errorBody: ErrorMessageModel = if (body == null) {
            ErrorMessageModel(status = status.value(), detail = ex.message as String)
        } else {
            body as ErrorMessageModel
        }
        return super.handleExceptionInternal(ex, errorBody, headers, status, request)
    }
}