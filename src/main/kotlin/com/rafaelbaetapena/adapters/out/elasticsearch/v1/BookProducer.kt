package com.rafaelbaetapena.adapters.out.elasticsearch.v1

import com.fasterxml.jackson.databind.ObjectMapper
import com.rafaelbaetapena.application.domain.Book
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.RequestOptions
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.common.xcontent.XContentType
import java.util.*

class BookProducer(
    private val elasticsearch: RestHighLevelClient,
    private val jackson: ObjectMapper
) {
    fun send(index: String, book: Book) {
        val booksAsJson = jackson.writeValueAsString(book)

        val document = HashMap<String, String>()
        document["value"] = booksAsJson

        val indexRequest = IndexRequest()
            .index(index)
            .id(UUID.randomUUID().toString())
            .source(document, XContentType.JSON)

        elasticsearch.index(indexRequest, RequestOptions.DEFAULT)
    }
}