package com.rafaelbaetapena.adapters.out.persistence.v1

import com.rafaelbaetapena.adapters.out.persistence.v1.repositories.BookRepository
import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.port.out.FindBookByIdAdapter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Singleton

@Singleton
class FindBookByIdAdapterImpl(
        private val bookRepository: BookRepository
): FindBookByIdAdapter {

    override fun execute(id: UUID): Book? {

        log.info("$CLASS_NAME starting find book by id")

        val book = bookRepository.findById(id)
                .map { it.toDomain() }
                .orElse(null)

        log.info("$CLASS_NAME finalized find book by id")

        return book
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(FindBookByIdAdapterImpl::class.java)
        private val CLASS_NAME = "[${FindBookByIdAdapterImpl::class.java}]"
    }
}