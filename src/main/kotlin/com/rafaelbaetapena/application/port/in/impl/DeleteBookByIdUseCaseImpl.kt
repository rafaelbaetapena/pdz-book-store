package com.rafaelbaetapena.application.port.`in`.impl

import com.rafaelbaetapena.application.port.`in`.DeleteBookByIdUseCase
import com.rafaelbaetapena.application.port.`in`.FindBookByIdUseCase
import com.rafaelbaetapena.application.port.out.DeleteBookByIdAdapter
import java.util.*
import javax.inject.Singleton

@Singleton
class DeleteBookByIdUseCaseImpl(
        private val deleteBookByIdAdapter: DeleteBookByIdAdapter,
        private val findBookByIdUseCase: FindBookByIdUseCase
): DeleteBookByIdUseCase {

    override fun execute(bookId: UUID) {
        val book = findBookByIdUseCase.execute(bookId)
        deleteBookByIdAdapter.execute(book)
    }
}