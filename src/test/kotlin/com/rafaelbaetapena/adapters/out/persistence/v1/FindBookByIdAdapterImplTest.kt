package com.rafaelbaetapena.adapters.out.persistence.v1

import com.rafaelbaetapena.adapters.out.persistence.v1.entities.BookEntity
import com.rafaelbaetapena.adapters.out.persistence.v1.repositories.BookRepository
import com.rafaelbaetapena.application.domain.BookCategory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import org.slf4j.LoggerFactory
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class FindBookByIdAdapterImplTest {

    companion object{
        private val LOG = LoggerFactory.getLogger(FindBookByIdAdapterImplTest::class.java)
    }

    @Mock
    lateinit var bookRepository: BookRepository

    @InjectMocks
    lateinit var findBookByIdAdapterImpl: FindBookByIdAdapterImpl

    @Test
    fun `given an existing book id then the book data should be returning`() {

        val id = UUID.fromString("f680eba8-d0ab-4a95-8ec7-ee6ed4716606")
        val bookEntity = BookEntity(
                id = id,
                name = "The Hobbit",
                author = "J.R.R. Tolkien",
                publisher = "HarperCollins Publishers",
                numberOfPages = 400,
                category = BookCategory.FANTASY)

        whenever(bookRepository.findById(id)).thenReturn(Optional.of(bookEntity))

        val actual = findBookByIdAdapterImpl.execute(id)
        assertNotNull(actual)
        assertEquals(bookEntity.toDomain(), actual)
        LOG.info("Returned book: $actual")
    }
}