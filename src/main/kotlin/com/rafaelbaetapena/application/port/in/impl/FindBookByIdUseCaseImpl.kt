package com.rafaelbaetapena.application.port.`in`.impl

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.exceptions.FindBookByIdException
import com.rafaelbaetapena.application.port.`in`.FindBookByIdUseCase
import com.rafaelbaetapena.application.port.out.FindBookByIdAdapter
import java.util.*
import javax.inject.Singleton

@Singleton
class FindBookByIdUseCaseImpl(private val findBookByIdAdapter: FindBookByIdAdapter):
        FindBookByIdUseCase {

    override fun execute(id: UUID): Book {
        return findBookByIdAdapter.execute(id) ?: throw FindBookByIdException()
    }
}