package com.rafaelbaetapena.application.port.`in`.impl

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.domain.BookFilter
import com.rafaelbaetapena.application.port.`in`.FindAllBooksUseCase
import com.rafaelbaetapena.application.port.out.FindAllBooksAdapter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class FindAllBooksUseCaseImpl(private val findAllBooksAdapter: FindAllBooksAdapter):
        FindAllBooksUseCase {

    override fun execute(filters: BookFilter): List<Book> {

        log.info("$CLASS_NAME starting find all books")

        val books = findAllBooksAdapter.execute(filters)

        log.info("$CLASS_NAME finalized find all books")

        return books
    }
    companion object {
        private val log: Logger = LoggerFactory.getLogger(FindAllBooksUseCaseImpl::class.java)
        private val CLASS_NAME = "[${FindAllBooksUseCaseImpl::class.java}]"
    }
}