package com.rafaelbaetapena.adapters.`in`.web.v1.controllers

import com.rafaelbaetapena.adapters.`in`.web.v1.requests.CreateBookRequest
import com.rafaelbaetapena.adapters.`in`.web.v1.responses.CreateBookResponse
import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.domain.BookCategory
import com.rafaelbaetapena.application.exceptions.FindBookByIdException
import com.rafaelbaetapena.application.port.`in`.CreateBookUseCase
import com.rafaelbaetapena.application.port.`in`.FindAllBooksUseCase
import com.rafaelbaetapena.application.port.`in`.FindBookByIdUseCase
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
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class BooksControllerTest {

    companion object{
        private val log = LoggerFactory.getLogger(BooksControllerTest::class.java)
    }

    @Mock
    lateinit var createBookUseCase: CreateBookUseCase

    @Mock
    lateinit var findAllBooksUseCase: FindAllBooksUseCase

    @Mock
    lateinit var findBookByIdUseCase: FindBookByIdUseCase

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
        assertEquals(HttpStatus.CREATED, actual.status)
        assertNotNull(actual.body())
        assertTrue(ReflectionEquals(bookResponse).matches(actual.body()))
        assertNotNull(actual.body()?.id)
        assertEquals(bookResponse.id, actual.body()?.id)
        log.info("Book created: $actual")
    }

    @Test
    fun `given the filter values then returns all found books`() {

        val book = Book(
                name = "The Hobbit",
                author = "J.R.R. Tolkien",
                publisher = "HarperCollins Publishers",
                numberOfPages = 400,
                category = BookCategory.FANTASY)
        whenever(findAllBooksUseCase.execute(any()))
                .thenReturn(listOf(book))

        val actual = booksController.findAll(
                name = "The Hobbit",
                author = "J.R.R. Tolkien",
                publisher = "HarperCollins Publishers",
                category = BookCategory.FANTASY
        )
        assertNotNull(actual)
        assertEquals(HttpStatus.OK, actual.status)
        assertNotNull(actual.body())
        assertNotNull(actual.body()?.books)
        assertFalse(actual.body()?.books!!.isEmpty())
        log.info("Book found: $actual")
    }

    @Test
    fun `given an existing book id then the book data should be returning`() {

        val book = Book(
                name = "The Hobbit",
                author = "J.R.R. Tolkien",
                publisher = "HarperCollins Publishers",
                numberOfPages = 400,
                category = BookCategory.FANTASY)
        whenever(findBookByIdUseCase.execute(any()))
                .thenReturn(book)

        val actual = booksController.findById(bookId = book.id)
        assertNotNull(actual)
        assertEquals(HttpStatus.OK, actual.status)
        assertNotNull(actual.body())
        log.info("Book found: $actual")
    }

    @Test
    fun `given a non-existent book id then it should return an exception`() {

        whenever(findBookByIdUseCase.execute(any()))
                .thenThrow(FindBookByIdException::class.java)

        val actual = booksController.findById(bookId = UUID.randomUUID())
        assertNotNull(actual)
        assertEquals(HttpStatus.NOT_FOUND, actual.status)
        assertNotNull(actual.body())
        log.info("Book found: $actual")
    }
}