package com.rafaelbaetapena.application.`in`.usecases.impl

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.domain.BookFilter
import com.rafaelbaetapena.application.`in`.usecases.FindAllBooksUseCase
import com.rafaelbaetapena.application.out.port.FindAllBooksPort
import javax.inject.Singleton

@Singleton
class FindAllBooksUseCaseImpl(private val findAllBooksPort: FindAllBooksPort):
        FindAllBooksUseCase {

    override fun execute(filters: BookFilter): List<Book> {
        return findAllBooksPort.execute(filters)
    }
}