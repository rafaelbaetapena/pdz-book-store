package com.rafaelbaetapena.application.port.`in`.impl

import com.rafaelbaetapena.application.port.`in`.DeleteBookByIdUseCase
import com.rafaelbaetapena.application.port.`in`.FindBookByIdUseCase
import com.rafaelbaetapena.application.port.out.DeleteBookByIdAdapter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Singleton

@Singleton
class DeleteBookByIdUseCaseImpl(
        private val deleteBookByIdAdapter: DeleteBookByIdAdapter,
        private val findBookByIdUseCase: FindBookByIdUseCase
): DeleteBookByIdUseCase {

    override fun execute(bookId: UUID) {

        log.info("$CLASS_NAME starting delete book by id")

        val book = findBookByIdUseCase.execute(bookId)
        deleteBookByIdAdapter.execute(book)

        log.info("$CLASS_NAME finalized delete book by id")
    }
    companion object {
        private val log: Logger = LoggerFactory.getLogger(DeleteBookByIdUseCaseImpl::class.java)
        private val CLASS_NAME = "[${DeleteBookByIdUseCaseImpl::class.java}]"
    }
}