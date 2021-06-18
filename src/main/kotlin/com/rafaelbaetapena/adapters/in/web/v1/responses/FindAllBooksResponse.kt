package com.rafaelbaetapena.adapters.`in`.web.v1.responses

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.domain.BookCategory
import java.util.*

data class FindAllBooksResponse(val books: List<BookResponse>) {
    data class BookResponse(
            val id: UUID,
            val name: String,
            val author: String,
            val publisher: String,
            val numberOfPages: Int,
            val category: BookCategory
    ) {
        constructor(
                book: Book
        ) : this(
                book.id,
                book.name,
                book.author,
                book.publisher,
                book.numberOfPages,
                book.category)
    }
}
