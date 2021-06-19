package com.rafaelbaetapena.adapters.`in`.web.v1.handlers

import io.micronaut.http.HttpStatus

data class ErrorResponse(
        val message: String,
        val httpStatus: HttpStatus
)
