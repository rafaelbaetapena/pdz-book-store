package com.rafaelbaetapena.application.out.port

import com.rafaelbaetapena.application.domain.Book

interface CreateBookPort {
    fun execute(book: Book): Book
}
