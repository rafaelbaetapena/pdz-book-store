package com.rafaelbaetapena.application.`in`.usecases.impl

import com.rafaelbaetapena.application.`in`.domain.Book
import com.rafaelbaetapena.application.`in`.usecases.CreateBookUseCase
import com.rafaelbaetapena.application.out.port.CreateBookPort

class CreateBookUseCaseImpl(private val createBookPort: CreateBookPort):
        CreateBookUseCase {

    override fun execute(book: Book): Book {
        return createBookPort.execute(book)
    }
}