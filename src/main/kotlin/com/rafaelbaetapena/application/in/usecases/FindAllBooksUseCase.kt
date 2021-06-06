package com.rafaelbaetapena.application.`in`.usecases

import com.rafaelbaetapena.application.`in`.domain.Book
import com.rafaelbaetapena.application.`in`.domain.BookFilter

interface FindAllBooksUseCase {

    fun execute(filters: BookFilter): List<Book>
}