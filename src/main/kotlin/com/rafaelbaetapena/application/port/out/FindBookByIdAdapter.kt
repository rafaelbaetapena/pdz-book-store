package com.rafaelbaetapena.application.port.out

import com.rafaelbaetapena.application.domain.Book
import java.util.*

interface FindBookByIdAdapter {

    fun execute(id: UUID): Book?
}
