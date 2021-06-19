package com.rafaelbaetapena.adapters.`in`.web.v1.handlers

import com.rafaelbaetapena.application.exceptions.FindBookByIdException
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.server.exceptions.ExceptionHandler
import javax.inject.Singleton

@Singleton
class FindBookByIdExceptionHandler: ExceptionHandler<FindBookByIdException, HttpResponse<ErrorResponse>> {
    override fun handle(request: HttpRequest<*>?, exception: FindBookByIdException?): HttpResponse<ErrorResponse> {
        val error = ErrorResponse(
                "Book not found",
                HttpStatus.NOT_FOUND
        )
        return HttpResponse.notFound(error)
    }
}