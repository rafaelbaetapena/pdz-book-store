package com.rafaelbaetapena.application.port.out

import java.util.*

interface DeleteBookByIdAdapter {
    fun execute(bookId: UUID)
}
