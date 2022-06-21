package com.example.restaurante.exceptions

class RestaurantNotFoundException(val exceptionMessage: String?): RuntimeException(exceptionMessage) {
}