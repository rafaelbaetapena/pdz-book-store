package com.rafaelbaetapena.application.domain

data class BookFilter(
        val name: String? = null,
        val author: String? = null,
        val publisher: String? = null,
        val category: BookCategory? = null
)
