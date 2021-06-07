package com.rafaelbaetapena.application.`in`.usecases

import com.rafaelbaetapena.application.domain.Book

interface CreateBookUseCase {

    fun execute(book: Book): Book
}