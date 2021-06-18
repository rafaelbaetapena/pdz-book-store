package com.rafaelbaetapena.adapters.`in`.web.v1.requests

import com.rafaelbaetapena.application.domain.BookCategory
import com.rafaelbaetapena.application.domain.BookFilter

data class FindAllBooksRequest(
        val name: String? = null,
        val author: String? = null,
        val publisher: String? = null,
        val category: BookCategory? = null
) {
    fun toDomain(): BookFilter =
            BookFilter(this.name, this.author, this.publisher, this.category)
}
