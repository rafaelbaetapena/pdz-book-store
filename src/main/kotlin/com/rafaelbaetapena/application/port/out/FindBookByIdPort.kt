package com.rafaelbaetapena.application.port.out

import com.rafaelbaetapena.application.domain.Book
import java.util.*

interface FindBookByIdPort {

    fun execute(id: UUID): Book?
}
