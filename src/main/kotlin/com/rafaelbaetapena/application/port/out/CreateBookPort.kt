package com.rafaelbaetapena.application.port.out

import com.rafaelbaetapena.application.domain.Book

interface CreateBookPort {
    fun execute(book: Book): Book
}
