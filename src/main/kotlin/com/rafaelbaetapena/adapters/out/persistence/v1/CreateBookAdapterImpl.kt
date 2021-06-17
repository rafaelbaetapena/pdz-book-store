package com.rafaelbaetapena.adapters.out.persistence.v1

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.port.out.CreateBookAdapter
import javax.inject.Singleton

@Singleton
class CreateBookAdapterImpl: CreateBookAdapter {
    override fun execute(book: Book): Book = book
}