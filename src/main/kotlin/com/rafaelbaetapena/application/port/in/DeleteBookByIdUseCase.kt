package com.rafaelbaetapena.application.port.`in`

import java.util.*

interface DeleteBookByIdUseCase {
    fun execute(bookId: UUID)
}