package com.rafaelbaetapena.application.port.`in`.impl

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.exceptions.FindBookByIdException
import com.rafaelbaetapena.application.port.`in`.FindBookByIdUseCase
import com.rafaelbaetapena.application.port.out.FindBookByIdAdapter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Singleton

@Singleton
class FindBookByIdUseCaseImpl(
        private val findBookByIdAdapter: FindBookByIdAdapter
): FindBookByIdUseCase {

    override fun execute(id: UUID): Book {

        log.info("$CLASS_NAME starting find book by id")

        val book = findBookByIdAdapter.execute(id) ?: throw FindBookByIdException()

        log.info("$CLASS_NAME finalized find book by id")

        return book
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(FindBookByIdUseCaseImpl::class.java)
        private val CLASS_NAME = "[${FindBookByIdUseCaseImpl::class.java}]"
    }
}