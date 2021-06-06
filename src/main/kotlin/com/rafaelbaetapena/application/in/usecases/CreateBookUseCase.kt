package com.rafaelbaetapena.application.`in`.usecases

import com.rafaelbaetapena.application.`in`.domain.Book

interface CreateBookUseCase {

    fun execute(book: Book): Book
}