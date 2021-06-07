package com.rafaelbaetapena.application.port.`in`

import com.rafaelbaetapena.application.domain.Book

interface CreateBookUseCase {

    fun execute(book: Book): Book
}