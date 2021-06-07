package com.rafaelbaetapena.application.port.`in`.impl

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.exceptions.FindBookByIdException
import com.rafaelbaetapena.application.port.`in`.FindBookByIdUseCase
import com.rafaelbaetapena.application.port.out.FindBookByIdPort
import java.util.*
import javax.inject.Singleton

@Singleton
class FindBookByIdUseCaseImpl(private val findBookByIdPort: FindBookByIdPort):
        FindBookByIdUseCase {

    override fun execute(id: UUID): Book {
        return findBookByIdPort.execute(id) ?: throw FindBookByIdException()
    }
}