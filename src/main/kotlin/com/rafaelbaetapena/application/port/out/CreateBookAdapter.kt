package com.rafaelbaetapena.application.port.out

import com.rafaelbaetapena.application.domain.Book

interface CreateBookAdapter {
    fun execute(book: Book): Book
}
