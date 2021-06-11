package com.rafaelbaetapena.adapters.out.persistence.v1

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.port.out.CreateBookPort
import javax.inject.Singleton

@Singleton
class CreateBookPortImpl: CreateBookPort {
    override fun execute(book: Book): Book = book
}