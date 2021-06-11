package com.rafaelbaetapena.adapters.`in`.web.v1.controllers

import com.rafaelbaetapena.adapters.`in`.web.v1.requests.CreateBookRequest
import com.rafaelbaetapena.adapters.`in`.web.v1.responses.CreateBookResponse
import com.rafaelbaetapena.application.port.`in`.CreateBookUseCase
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller("/v1/books")
class BooksController(
        private val createBookUseCase: CreateBookUseCase
) {

    @Post
    fun create(@Body request: CreateBookRequest): HttpResponse<CreateBookResponse> {

        log.info("$CLASS_NAME starting create book")

        val createdBook = createBookUseCase.execute(request.toDomain())

        log.info("$CLASS_NAME finalized create book")

        return HttpResponse.ok(CreateBookResponse(createdBook))
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(BooksController::class.java)
        private val CLASS_NAME = "[${BooksController::class.java}]"
    }
}