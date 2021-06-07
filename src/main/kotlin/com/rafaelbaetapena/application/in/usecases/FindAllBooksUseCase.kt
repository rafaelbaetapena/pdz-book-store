package com.rafaelbaetapena.application.`in`.usecases

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.domain.BookFilter

interface FindAllBooksUseCase {

    fun execute(filters: BookFilter): List<Book>
}