package com.rafaelbaetapena.adapters.`in`.web.v1.requests

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.domain.BookCategory

data class CreateBookRequest(
        private val name: String,
        private val author: String,
        private val publisher: String,
        private val numberOfPages: Int,
        private val category: BookCategory
) {
    fun toDomain(): Book = Book(
            name = this.name,
            author = this.author,
            publisher = this.publisher,
            numberOfPages = this.numberOfPages,
            category = this.category
    )
}