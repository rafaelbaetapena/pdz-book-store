package com.rafaelbaetapena.application.out.port

import com.rafaelbaetapena.application.`in`.domain.Book
import java.util.*

interface FindBookByIdPort {

    fun execute(id: UUID): Book?
}
