package com.rafaelbaetapena.application.`in`.usecases.impl

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.domain.BookCategory
import com.rafaelbaetapena.application.port.`in`.impl.CreateBookUseCaseImpl
import com.rafaelbaetapena.application.port.out.CreateBookAdapter
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
    lateinit var createBookAdapter: CreateBookAdapter

    @InjectMocks
    lateinit var createBookUseCaseImpl: CreateBookUseCaseImpl

    @Test
    fun `given a new book when the user enters all the book data correctly then the book is successfully registered`() {

        val book = Book(
                name = "The Hobbit",
                author = "J.R.R. Tolkien",
                publisher = "HarperCollins Publishers",
                numberOfPages = 400,
                category = BookCategory.FANTASY)
        Mockito.`when`(createBookAdapter.execute(book)).thenReturn(book)

        val actual = createBookUseCaseImpl.execute(book)
        assertNotNull(actual)
        assertEquals(book, actual)
        assertNotNull(actual.id)
        log.info("Book created: $actual")
    }
}