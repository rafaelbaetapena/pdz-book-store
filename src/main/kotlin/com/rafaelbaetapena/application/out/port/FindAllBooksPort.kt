package com.rafaelbaetapena.application.out.port

import com.rafaelbaetapena.application.`in`.domain.Book
import com.rafaelbaetapena.application.`in`.domain.BookFilter

interface FindAllBooksPort {
    fun execute(filters: BookFilter): List<Book>
}
