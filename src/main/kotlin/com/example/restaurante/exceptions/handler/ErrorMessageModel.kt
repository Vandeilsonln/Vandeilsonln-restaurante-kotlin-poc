package com.example.restaurante.exceptions.handler

import java.time.LocalDateTime

data class ErrorMessageModel (var message: String?, val timestamp: String = LocalDateTime.now().toString())