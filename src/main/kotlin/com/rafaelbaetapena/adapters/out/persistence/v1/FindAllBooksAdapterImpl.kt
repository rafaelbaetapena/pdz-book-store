package com.rafaelbaetapena.adapters.out.persistence.v1

import com.rafaelbaetapena.adapters.out.persistence.v1.repositories.BookRepository
import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.domain.BookFilter
import com.rafaelbaetapena.application.port.out.FindAllBooksAdapter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class FindAllBooksAdapterImpl(
        private val bookRepository: BookRepository
): FindAllBooksAdapter {

    override fun execute(filters: BookFilter): List<Book> {

        log.info("$CLASS_NAME starting find all books")

        val books = bookRepository.findByFilters(filters)
                .map { it.toDomain() }

        log.info("$CLASS_NAME finalized find all books")

        return books
    }
    companion object {
        private val log: Logger = LoggerFactory.getLogger(FindAllBooksAdapterImpl::class.java)
        private val CLASS_NAME = "[${FindAllBooksAdapterImpl::class.java}]"
    }
}