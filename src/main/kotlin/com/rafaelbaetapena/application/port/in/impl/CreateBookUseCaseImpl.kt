package com.rafaelbaetapena.application.port.`in`.impl

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.port.`in`.CreateBookUseCase
import com.rafaelbaetapena.application.port.out.CreateBookPort
import javax.inject.Singleton

@Singleton
class CreateBookUseCaseImpl(private val createBookPort: CreateBookPort):
        CreateBookUseCase {

    override fun execute(book: Book): Book {
        return createBookPort.execute(book)
    }
}