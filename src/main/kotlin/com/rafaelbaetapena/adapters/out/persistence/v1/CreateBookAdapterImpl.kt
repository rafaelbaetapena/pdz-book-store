package com.rafaelbaetapena.adapters.out.persistence.v1

import com.rafaelbaetapena.adapters.out.persistence.v1.entities.BookEntity
import com.rafaelbaetapena.adapters.out.persistence.v1.repositories.BookRepository
import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.port.out.CreateBookAdapter
import javax.inject.Singleton

@Singleton
class CreateBookAdapterImpl(
    private val bookRepository: BookRepository
): CreateBookAdapter {

    override fun execute(book: Book): Book =
            bookRepository.save(BookEntity(book)).toDomain()
}