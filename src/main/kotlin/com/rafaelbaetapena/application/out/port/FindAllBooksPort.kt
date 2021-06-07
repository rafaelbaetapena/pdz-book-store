package com.rafaelbaetapena.application.out.port

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.domain.BookFilter

interface FindAllBooksPort {
    fun execute(filters: BookFilter): List<Book>
}
