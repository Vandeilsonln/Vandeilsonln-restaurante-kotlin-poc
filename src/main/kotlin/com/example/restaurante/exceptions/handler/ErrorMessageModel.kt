package com.example.restaurante.exceptions.handler

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ErrorMessageModel(
    var status: Int,
    var type: String? = null,
    var title: String? = null,
    var detail: String?
)