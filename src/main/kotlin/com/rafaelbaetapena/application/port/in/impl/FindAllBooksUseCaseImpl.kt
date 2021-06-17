package com.rafaelbaetapena.application.port.`in`.impl

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.domain.BookFilter
import com.rafaelbaetapena.application.port.`in`.FindAllBooksUseCase
import com.rafaelbaetapena.application.port.out.FindAllBooksAdapter
import javax.inject.Singleton

@Singleton
class FindAllBooksUseCaseImpl(private val findAllBooksAdapter: FindAllBooksAdapter):
        FindAllBooksUseCase {

    override fun execute(filters: BookFilter): List<Book> {
        return findAllBooksAdapter.execute(filters)
    }
}