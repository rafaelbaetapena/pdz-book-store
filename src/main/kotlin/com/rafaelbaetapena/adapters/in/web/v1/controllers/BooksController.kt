package com.rafaelbaetapena.adapters.`in`.web.v1.controllers

import com.rafaelbaetapena.adapters.`in`.web.v1.requests.CreateBookRequest
import com.rafaelbaetapena.adapters.`in`.web.v1.requests.FindAllBooksRequest
import com.rafaelbaetapena.adapters.`in`.web.v1.responses.CreateBookResponse
import com.rafaelbaetapena.adapters.`in`.web.v1.responses.FindAllBooksResponse
import com.rafaelbaetapena.adapters.`in`.web.v1.responses.FindBookByIdResponse
import com.rafaelbaetapena.application.domain.BookCategory
import com.rafaelbaetapena.application.port.`in`.CreateBookUseCase
import com.rafaelbaetapena.application.port.`in`.FindAllBooksUseCase
import com.rafaelbaetapena.application.port.`in`.FindBookByIdUseCase
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

@Controller("/v1/books")
class BooksController(
        private val createBookUseCase: CreateBookUseCase,
        private val findAllBooksUseCase: FindAllBooksUseCase,
        private val findBookByIdUseCase: FindBookByIdUseCase
) {

    @Post
    fun create(@Body request: CreateBookRequest): HttpResponse<CreateBookResponse> {

        log.info("$CLASS_NAME starting create book")

        val createdBook = createBookUseCase.execute(request.toDomain())

        log.info("$CLASS_NAME finalized create book")

        return HttpResponse.created(CreateBookResponse(createdBook))
    }

    @Get("{?name,author,publisher,category}")
    fun findAll(
            @QueryValue name: String?,
            @QueryValue author: String?,
            @QueryValue publisher: String?,
            @QueryValue category: BookCategory?): HttpResponse<FindAllBooksResponse> {

        log.info("$CLASS_NAME starting find all books")

        val findAllBooksRequest = FindAllBooksRequest(
                name = name,
                author = author,
                publisher = publisher,
                category = category
        )
        val books = findAllBooksUseCase.execute(findAllBooksRequest.toDomain())
                .map { FindAllBooksResponse.BookResponse(it) }

        log.info("$CLASS_NAME finalized find all books")

        return HttpResponse.ok(FindAllBooksResponse(books))
    }

    @Get("/{book_id}")
    fun findById(@PathVariable("book_id") bookId: UUID): HttpResponse<FindBookByIdResponse> {

        log.info("$CLASS_NAME starting find book by id")

        val book = findBookByIdUseCase.execute(bookId)

        log.info("$CLASS_NAME finalized find book by id")

        return HttpResponse.ok(FindBookByIdResponse(book))
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(BooksController::class.java)
        private val CLASS_NAME = "[${BooksController::class.java}]"
    }
}