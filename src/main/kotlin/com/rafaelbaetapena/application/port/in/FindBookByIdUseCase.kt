package com.rafaelbaetapena.application.port.`in`

import com.rafaelbaetapena.application.domain.Book
import java.util.*

interface FindBookByIdUseCase {

    fun execute(id: UUID): Book
}