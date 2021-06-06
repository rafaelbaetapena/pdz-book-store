package com.rafaelbaetapena.application.`in`.domain

import java.util.*

data class Book(
        val id: UUID,
        val name: String,
        val author: String,
        val publisher: String,
        val numberOfPages: Int,
        val category: BookCategory
) {
    constructor(
            name: String,
            author: String,
            publisher: String,
            numberOfPages: Int,
            category: BookCategory
    ) : this(UUID.randomUUID(), name, author, publisher, numberOfPages, category)
}