package com.rafaelbaetapena.adapters.out.persistence.v1

import com.rafaelbaetapena.adapters.out.persistence.v1.entities.BookEntity
import com.rafaelbaetapena.adapters.out.persistence.v1.repositories.BookRepository
import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.port.out.DeleteBookByIdAdapter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class DeleteBookByIdAdapterImpl(
        private val bookRepository: BookRepository
): DeleteBookByIdAdapter {
    override fun execute(book: Book) {

        log.info("$CLASS_NAME starting delete book by id")

        bookRepository.delete(BookEntity(book))

        log.info("$CLASS_NAME finalized delete book by id")
    }
    companion object {
        private val log: Logger = LoggerFactory.getLogger(DeleteBookByIdAdapterImpl::class.java)
        private val CLASS_NAME = "[${DeleteBookByIdAdapterImpl::class.java}]"
    }
}