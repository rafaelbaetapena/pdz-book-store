package com.rafaelbaetapena.application.out.port

import com.rafaelbaetapena.application.`in`.domain.Book

interface CreateBookPort {
    fun execute(book: Book): Book
}
