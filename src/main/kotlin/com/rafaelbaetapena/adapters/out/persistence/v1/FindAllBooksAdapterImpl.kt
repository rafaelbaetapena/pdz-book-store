package com.rafaelbaetapena.adapters.out.persistence.v1

import com.fasterxml.jackson.databind.ObjectMapper
import com.rafaelbaetapena.adapters.out.persistence.v1.repositories.BookRepository
import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.application.domain.BookFilter
import com.rafaelbaetapena.application.port.out.FindAllBooksAdapter
import io.lettuce.core.api.StatefulRedisConnection
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class FindAllBooksAdapterImpl(
        private val bookRepository: BookRepository,
        private val redis: StatefulRedisConnection<String, String>,
        private val jackson: ObjectMapper
): FindAllBooksAdapter {

    override fun execute(filters: BookFilter): List<Book> {

        LOG.info("$CLASS_NAME starting find all books")

        val cacheKey = "$PREFIX_CACHE_KEY::${filters}"

        val books = getBooksListFromCache(cacheKey) ?:
            getBooksListFromDb(cacheKey, filters)

        LOG.info("$CLASS_NAME finalized find all books")

        return books
    }

    private fun getBooksListFromCache(cacheKey: String): List<Book>? {
        val cachedBooksListJson = redis.sync().get(cacheKey) ?: return null

        val cachedBooksListObj = jackson.readValue<List<Book>>(cachedBooksListJson,
                jackson.typeFactory.constructCollectionType(List::class.java, Book::class.java)
        )

        LOG.info("$CLASS_NAME books list found in cache with key $cacheKey")

        return cachedBooksListObj
    }

    private fun getBooksListFromDb(cacheKey: String, filters: BookFilter): List<Book> {

        val books = bookRepository.findByFilters(filters)
                .map { it.toDomain() }

        setCache(cacheKey, books)

        LOG.info("$CLASS_NAME books list found in db with filters $filters")

        return books
    }

    private fun setCache(cacheKey: String, books: List<Book>) {
        val booksAsJson = jackson.writeValueAsString(books)
        val commands = redis.sync()
        commands.multi()
        commands.set(cacheKey, booksAsJson)
        commands.expire(cacheKey, 30)
        commands.exec()
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(FindAllBooksAdapterImpl::class.java)
        private val CLASS_NAME = "[${FindAllBooksAdapterImpl::class.java}]"
        private const val PREFIX_CACHE_KEY = "FIND_ALL_BOOKS"
    }
}