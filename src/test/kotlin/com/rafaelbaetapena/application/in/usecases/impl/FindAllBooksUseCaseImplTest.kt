package com.rafaelbaetapena.application.`in`.usecases.impl

import com.rafaelbaetapena.application.`in`.domain.Book
import com.rafaelbaetapena.application.`in`.domain.BookCategory
import com.rafaelbaetapena.application.`in`.domain.BookFilter
import com.rafaelbaetapena.application.out.port.FindAllBooksPort
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class FindAllBooksUseCaseImplTest {

    @Mock
    lateinit var findAllBooksPort: FindAllBooksPort

    @InjectMocks
    lateinit var findAllBooksUseCaseImpl: FindAllBooksUseCaseImpl

    @Test
    fun `given the filter values then returns all found books`() {

        val filters = BookFilter(name = "O Hobbit", publisher = "HarperCollins")
        val books = listOf(
                Book("O Hobbit", "J.R.R. Tolkien", "HarperCollins", 336, BookCategory.FANTASY)
        )
        Mockito.`when`(findAllBooksPort.execute(filters)).thenReturn(books)

        val actual = findAllBooksUseCaseImpl.execute(filters)
        assertNotNull(actual)
        assertFalse(actual.isEmpty())
        assertEquals(1, actual.size)
    }
}