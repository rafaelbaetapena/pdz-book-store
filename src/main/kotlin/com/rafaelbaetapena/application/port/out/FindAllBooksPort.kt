package com.rafaelbaetapena.application.port.out

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.domain.BookFilter

interface FindAllBooksPort {
    fun execute(filters: BookFilter): List<Book>
}
