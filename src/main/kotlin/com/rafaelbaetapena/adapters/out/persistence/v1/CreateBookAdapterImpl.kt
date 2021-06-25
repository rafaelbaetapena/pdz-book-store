package com.rafaelbaetapena.adapters.out.persistence.v1

import com.rafaelbaetapena.adapters.out.kafka.v1.CreateBookLogProducer
import com.rafaelbaetapena.adapters.out.persistence.v1.entities.BookEntity
import com.rafaelbaetapena.adapters.out.persistence.v1.repositories.BookRepository
import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.port.out.CreateBookAdapter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class CreateBookAdapterImpl(
    private val bookRepository: BookRepository,
    private val createBookLogProducer: CreateBookLogProducer
): CreateBookAdapter {

    override fun execute(book: Book): Book {

        LOG.info("$CLASS_NAME starting create book")

        val createdBook = bookRepository.save(BookEntity(book)).toDomain()
        createBookLogProducer.send(book.id, book)

        LOG.info("$CLASS_NAME finalized create book")

        return  createdBook
    }
    companion object {
        private val LOG = LoggerFactory.getLogger(CreateBookAdapterImpl::class.java)
        private val CLASS_NAME = "[${CreateBookAdapterImpl::class.java}]"
    }
}