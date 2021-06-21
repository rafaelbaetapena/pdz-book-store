package com.rafaelbaetapena.application.port.`in`.impl

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.domain.BookCategory
import com.rafaelbaetapena.application.domain.BookFilter
import com.rafaelbaetapena.application.port.out.FindAllBooksAdapter
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
internal class FindAllBooksUseCaseImplTest {

    companion object{
        private val log = LoggerFactory.getLogger(FindAllBooksUseCaseImplTest::class.java)
    }

    @Mock
    lateinit var findAllBooksAdapter: FindAllBooksAdapter

    @InjectMocks
    lateinit var findAllBooksUseCaseImpl: FindAllBooksUseCaseImpl

    @Test
    fun `given the filter values then returns all found books`() {

        val filters = BookFilter(name = "O Hobbit", publisher = "HarperCollins")
        val books = listOf(
                Book(
                    id = UUID.fromString("f680eba8-d0ab-4a95-8ec7-ee6ed4716606"),
                    name = "The Hobbit",
                    author = "J.R.R. Tolkien",
                    publisher = "HarperCollins Publishers",
                    numberOfPages = 400,
                    category = BookCategory.FANTASY)
        )
        Mockito.`when`(findAllBooksAdapter.execute(filters)).thenReturn(books)

        val actual = findAllBooksUseCaseImpl.execute(filters)
        assertNotNull(actual)
        assertFalse(actual.isEmpty())
        assertEquals(1, actual.size)
        log.info("Returned books: $actual")
    }
}