package com.rafaelbaetapena.application.port.out

import com.rafaelbaetapena.application.domain.Book

interface DeleteBookByIdAdapter {
    fun execute(book: Book)
}
