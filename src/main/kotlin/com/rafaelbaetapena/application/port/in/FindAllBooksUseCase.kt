package com.rafaelbaetapena.application.port.`in`

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.domain.BookFilter

interface FindAllBooksUseCase {

    fun execute(filters: BookFilter): List<Book>
}