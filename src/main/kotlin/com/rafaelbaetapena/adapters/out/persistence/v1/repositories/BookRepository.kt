package com.rafaelbaetapena.adapters.out.persistence.v1.repositories

import com.rafaelbaetapena.adapters.out.persistence.v1.entities.BookEntity
import com.rafaelbaetapena.application.domain.BookFilter
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.Parameter
import javax.persistence.TypedQuery
import kotlin.collections.ArrayList

@Repository
abstract class BookRepository(private val entityManager: EntityManager) :
        CrudRepository<BookEntity, UUID> {

    fun findByFilters(filters: BookFilter): List<BookEntity> {
        val query = getQueryFindByFilters(filters)

        return query.resultList
    }

    private fun getQueryFindByFilters(filters: BookFilter): TypedQuery<BookEntity> {
        val query = entityManager.createQuery(getQlStringFindByFilters(filters), BookEntity::class.java)

        if (!filters.name.isNullOrEmpty()) {
            query.setParameter("name", filters.name)
        }

        if (!filters.author.isNullOrEmpty()) {
            query.setParameter("author", filters.author)
        }

        if (!filters.publisher.isNullOrEmpty()) {
            query.setParameter("publisher", filters.publisher)
        }

        if (filters.category != null) {
            query.setParameter("category", filters.category)
        }

        return query
    }

    private fun getQlStringFindByFilters(filters: BookFilter): String {
        var qlString = "FROM books AS boo WHERE 1 = 1"

        if(!filters.name.isNullOrEmpty()){
            qlString = "$qlString  and boo.name = :name"
        }

        if(!filters.author.isNullOrEmpty()){
            qlString = "$qlString  and boo.author = :author"
        }

        if(!filters.publisher.isNullOrEmpty()){
            qlString = "$qlString  and boo.publisher = :publisher"
        }

        if(filters.category != null){
            qlString = "$qlString  and boo.category = :category"
        }

        return qlString
    }
}