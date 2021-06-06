package com.rafaelbaetapena.application.`in`.usecases.impl

import com.rafaelbaetapena.application.`in`.domain.Book
import com.rafaelbaetapena.application.`in`.domain.BookCategory
import com.rafaelbaetapena.application.out.port.CreateBookPort
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.slf4j.LoggerFactory

@ExtendWith(MockitoExtension::class)
internal class CreateBookUseCaseImplTest {

    companion object{
        private val log = LoggerFactory.getLogger(CreateBookUseCaseImplTest::class.java)
    }

    @Mock
    lateinit var createBookPort: CreateBookPort

    @InjectMocks
    lateinit var createBookUseCaseImpl: CreateBookUseCaseImpl

    @Test
    fun `given a new book when the user enters all the book data correctly then the book is successfully registered`() {

        val book = Book("O Hobbit", "J.R.R. Tolkien", "HarperCollins", 336, BookCategory.FANTASY)
        Mockito.`when`(createBookPort.execute(book)).thenReturn(book)

        val actual = createBookUseCaseImpl.execute(book)
        assertNotNull(actual)
        assertEquals(book, actual)
        assertNotNull(actual.id)
        log.info("Book created: $actual")
    }
}