package com.rafaelbaetapena.adapters.out.kafka.v1

import com.rafaelbaetapena.adapters.out.elasticsearch.v1.BookProducer
import com.rafaelbaetapena.application.domain.Book
import com.rafaelbaetapena.configurations.BookStoreLogConfiguration.Companion.INDEX_PREFIX
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.Topic
import org.slf4j.LoggerFactory

@KafkaListener(
    clientId = "pdz-book-store-micronaut-create-book-log-consumer",
    groupId = "create-book-log-consumer",
    batch = false,
    offsetReset = OffsetReset.EARLIEST
)
class CreateBookLogConsumer(
    private val elasticsearch: BookProducer
) {

    @Topic("create-book-log")
    fun receive(book: Book) {
        LOG.info("$CLASS_NAME Consuming of created book $book")
        elasticsearch.send(LOG_INDEX, book)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(CreateBookLogConsumer::class.java)
        private val CLASS_NAME = "[${CreateBookLogConsumer::class.java}]"
        private val LOG_INDEX = "$INDEX_PREFIX-create-book-log-index"
    }
}