package com.rafaelbaetapena.application.port.`in`.impl

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.port.`in`.CreateBookUseCase
import com.rafaelbaetapena.application.port.out.CreateBookAdapter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class CreateBookUseCaseImpl(private val createBookAdapter: CreateBookAdapter):
        CreateBookUseCase {

    override fun execute(book: Book): Book {

        log.info("$CLASS_NAME starting create book")

        val createdBook = createBookAdapter.execute(book)

        log.info("$CLASS_NAME finalized create book")

        return createdBook
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(CreateBookUseCaseImpl::class.java)
        private val CLASS_NAME = "[${CreateBookUseCaseImpl::class.java}]"
    }
}