package com.rafaelbaetapena.application.port.`in`.impl

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.port.`in`.CreateBookUseCase
import com.rafaelbaetapena.application.port.out.CreateBookAdapter
import javax.inject.Singleton

@Singleton
class CreateBookUseCaseImpl(private val createBookAdapter: CreateBookAdapter):
        CreateBookUseCase {

    override fun execute(book: Book): Book {
        return createBookAdapter.execute(book)
    }
}