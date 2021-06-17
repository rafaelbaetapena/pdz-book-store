package com.rafaelbaetapena.adapters.`in`.web.v1.controllers

import com.rafaelbaetapena.adapters.`in`.web.v1.requests.CreateBookRequest
import com.rafaelbaetapena.adapters.`in`.web.v1.responses.CreateBookResponse
import com.rafaelbaetapena.application.domain.BookCategory
import com.rafaelbaetapena.application.port.`in`.CreateBookUseCase
import io.micronaut.http.HttpStatus
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.internal.matchers.apachecommons.ReflectionEquals
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.slf4j.LoggerFactory

@ExtendWith(MockitoExtension::class)
internal class BooksControllerTest {

    companion object{
        private val log = LoggerFactory.getLogger(BooksControllerTest::class.java)
    }

    @Mock
    lateinit var createBookUseCase: CreateBookUseCase

    @InjectMocks
    lateinit var booksController: BooksController

    @Test
    fun `given a new book when the user enters all the book data correctly then the book is successfully registered`() {

        val createBookRequest = CreateBookRequest(
                name = "The Hobbit",
                author = "J.R.R. Tolkien",
                publisher = "HarperCollins Publishers",
                numberOfPages = 400,
                category = BookCategory.FANTASY)
        val book = createBookRequest.toDomain()
        val bookResponse = CreateBookResponse(book)
        whenever(createBookUseCase.execute(any()))
                .thenReturn(book)

        val actual = booksController.create(createBookRequest)
        assertNotNull(actual)
        assertEquals(HttpStatus.OK, actual.status)
        assertNotNull(actual.body())
        assertTrue(ReflectionEquals(bookResponse).matches(actual.body()))
        assertNotNull(actual.body()?.id)
        assertEquals(bookResponse.id, actual.body()?.id)
        log.info("Book created: $actual")
    }
}