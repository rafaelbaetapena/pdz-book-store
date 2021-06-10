package com.rafaelbaetapena.application.`in`.usecases.impl

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.domain.BookCategory
import com.rafaelbaetapena.application.exceptions.FindBookByIdException
import com.rafaelbaetapena.application.port.`in`.impl.FindBookByIdUseCaseImpl
import com.rafaelbaetapena.application.port.out.FindBookByIdPort
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.slf4j.LoggerFactory
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class FindBookByIdUseCaseImplTest{

    companion object{
        private val log = LoggerFactory.getLogger(FindBookByIdUseCaseImplTest::class.java)
    }

    @Mock
    lateinit var findBookByIdPort: FindBookByIdPort

    @InjectMocks
    lateinit var findBookByIdUseCaseImpl: FindBookByIdUseCaseImpl

    @Test
    fun `given an existing book id then the book data should be returning`() {

        val id = UUID.fromString("f680eba8-d0ab-4a95-8ec7-ee6ed4716606")
        val book = Book(
                    id = id,
                    name = "The Hobbit",
                    author = "J.R.R. Tolkien",
                    publisher = "HarperCollins Publishers",
                    numberOfPages = 400,
                    category = BookCategory.FANTASY)

        Mockito.`when`(findBookByIdPort.execute(id)).thenReturn(book)

        val actual = findBookByIdUseCaseImpl.execute(id)
        assertNotNull(actual)
        assertEquals(book, actual)
        log.info("Returned book: $actual")
    }

    @Test
    fun `given a non-existent book id then it should return an exception`() {

        val id = UUID.fromString("f680eba8-d0ab-4a95-8ec7-ee6ed4716606")
        Mockito.`when`(findBookByIdPort.execute(id)).thenReturn(null)

        val assertThrows = assertThrows(FindBookByIdException::class.java) { findBookByIdUseCaseImpl.execute(id) }
        log.info("Returned book: $assertThrows")
    }
}