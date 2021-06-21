package com.rafaelbaetapena.application.port.`in`.impl

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.domain.BookCategory
import com.rafaelbaetapena.application.port.`in`.FindBookByIdUseCase
import com.rafaelbaetapena.application.port.out.DeleteBookByIdAdapter
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.whenever
import org.slf4j.LoggerFactory
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class DeleteBookByIdUseCaseImplTest {

    companion object{
        private val log = LoggerFactory.getLogger(DeleteBookByIdUseCaseImplTest::class.java)
    }

    @Mock
    lateinit var deleteBookByIdAdapter: DeleteBookByIdAdapter

    @Mock
    lateinit var findBookByIdUseCase: FindBookByIdUseCase

    @InjectMocks
    lateinit var deleteBookByIdUseCaseImpl: DeleteBookByIdUseCaseImpl

    @Test
    fun `given an existing book id then the book data should be deleted`() {

        val id = UUID.fromString("f680eba8-d0ab-4a95-8ec7-ee6ed4716606")
        val book = Book(
                id = id,
                name = "The Hobbit",
                author = "J.R.R. Tolkien",
                publisher = "HarperCollins Publishers",
                numberOfPages = 400,
                category = BookCategory.FANTASY)

        whenever(findBookByIdUseCase.execute(id)).thenReturn(book)

        doNothing().whenever(deleteBookByIdAdapter).execute(id)

        deleteBookByIdUseCaseImpl.execute(id)
        log.info("Deleted book: $id")
    }
}