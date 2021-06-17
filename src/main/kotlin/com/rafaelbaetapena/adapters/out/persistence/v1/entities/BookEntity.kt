package com.rafaelbaetapena.adapters.out.persistence.v1.entities

import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.domain.BookCategory
import java.util.*
import javax.persistence.*

@Entity(name = "book")
@Table(name = "books", schema = "pdz")
data class BookEntity(
        @Id
        val id: UUID,
        val name: String,
        val author: String,
        val publisher: String,
        @Column(name = "number_of_pages")
        val numberOfPages: Int,
        @Enumerated(EnumType.STRING)
        val category: BookCategory
) {
    constructor(
            book: Book
    ) : this(book.id, book.name, book.author, book.publisher, book.numberOfPages, book.category)

    fun toDomain(): Book =
            Book(this.id, this.name, this.author, this.publisher, this.numberOfPages, this.category)
}
