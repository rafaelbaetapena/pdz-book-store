package com.rafaelbaetapena.adapters.out.persistence.v1

import com.rafaelbaetapena.adapters.out.persistence.v1.entities.BookEntity
import com.rafaelbaetapena.adapters.out.persistence.v1.repositories.BookRepository
import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.port.out.DeleteBookByIdAdapter

class DeleteBookByIdAdapterImpl(
        private val bookRepository: BookRepository
): DeleteBookByIdAdapter {
    override fun execute(book: Book) {
        bookRepository.delete(BookEntity(book))
    }
}