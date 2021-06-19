package com.rafaelbaetapena.adapters.out.persistence.v1.repositories

import com.rafaelbaetapena.adapters.out.persistence.v1.entities.BookEntity
import com.rafaelbaetapena.application.domain.BookFilter
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import io.micronaut.transaction.annotation.ReadOnly
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.TypedQuery

@Repository
abstract class BookRepository(private val entityManager: EntityManager) :
        CrudRepository<BookEntity, UUID> {

    @ReadOnly
    fun findByFilters(filters: BookFilter): List<BookEntity> {

        log.info("$CLASS_NAME starting find all books")

        val books = getQueryFindByFilters(filters).resultList

        log.info("$CLASS_NAME finalized find all books")

        return books
    }

    private fun getQueryFindByFilters(filters: BookFilter): TypedQuery<BookEntity> {

        val query = entityManager.createQuery(getQlStringFindByFilters(filters), BookEntity::class.java)

        if (!filters.name.isNullOrEmpty()) {
            query.setParameter("name", "%${filters.name}%")
        }

        if (!filters.author.isNullOrEmpty()) {
            query.setParameter("author", "%${filters.author}%")
        }

        if (!filters.publisher.isNullOrEmpty()) {
            query.setParameter("publisher", "%${filters.publisher}%")
        }

        if (filters.category != null) {
            query.setParameter("category", filters.category)
        }

        return query
    }

    private fun getQlStringFindByFilters(filters: BookFilter): String {
        var qlString = "FROM books AS boo WHERE 1 = 1"

        if(!filters.name.isNullOrEmpty()){
            qlString = "$qlString  and boo.name like :name"
        }

        if(!filters.author.isNullOrEmpty()){
            qlString = "$qlString  and boo.author like :author"
        }

        if(!filters.publisher.isNullOrEmpty()){
            qlString = "$qlString  and boo.publisher like :publisher"
        }

        if(filters.category != null){
            qlString = "$qlString  and boo.category = :category"
        }

        log.info("$CLASS_NAME qlString created: $qlString")

        return qlString
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(BookRepository::class.java)
        private val CLASS_NAME = "[${BookRepository::class.java}]"
    }
}