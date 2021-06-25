package com.rafaelbaetapena.adapters.out.persistence.v1

import com.rafaelbaetapena.adapters.out.kafka.v1.CreateBookLogProducer
import com.rafaelbaetapena.adapters.out.persistence.v1.entities.BookEntity
import com.rafaelbaetapena.adapters.out.persistence.v1.repositories.BookRepository
import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.domain.BookCategory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.whenever
import org.slf4j.LoggerFactory

@ExtendWith(MockitoExtension::class)
internal class CreateBookAdapterImplTest {

    companion object{
        private val LOG = LoggerFactory.getLogger(CreateBookAdapterImplTest::class.java)
    }

    @Mock
    lateinit var bookRepository: BookRepository

    @Mock
    lateinit var createBookLogProducer: CreateBookLogProducer

    @InjectMocks
    lateinit var createBookAdapterImpl: CreateBookAdapterImpl

    @Test
    fun `given a new book when the user enters all the book data correctly then the book is successfully registered`() {

        val book = Book(
                name = "The Hobbit",
                author = "J.R.R. Tolkien",
                publisher = "HarperCollins Publishers",
                numberOfPages = 400,
                category = BookCategory.FANTASY)
        val bookEntity = BookEntity(book)

        whenever(bookRepository.save(bookEntity)).thenReturn(bookEntity)
        doNothing().whenever(createBookLogProducer).send(any(), any())

        val actual = createBookAdapterImpl.execute(book)
        assertNotNull(actual)
        assertEquals(book, actual)
        assertNotNull(actual.id)
        LOG.info("Book created: $actual")
    }
}