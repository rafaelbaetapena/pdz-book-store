package com.rafaelbaetapena.application.`in`.usecases

import com.rafaelbaetapena.application.`in`.domain.Book
import java.util.*

interface FindBookByIdUseCase {

    fun execute(id: UUID): Book?
}