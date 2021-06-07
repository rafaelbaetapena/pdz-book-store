package com.rafaelbaetapena.application.`in`.usecases.impl

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.`in`.usecases.CreateBookUseCase
import com.rafaelbaetapena.application.out.port.CreateBookPort
import javax.inject.Singleton

@Singleton
class CreateBookUseCaseImpl(private val createBookPort: CreateBookPort):
        CreateBookUseCase {

    override fun execute(book: Book): Book {
        return createBookPort.execute(book)
    }
}