package com.rafaelbaetapena.adapters.out.kafka.v1

import com.rafaelbaetapena.application.domain.Book
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.Topic
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@KafkaListener(
    clientId = "pdz-book-store-micronaut-create-book-log-consumer",
    groupId = "create-book-log-consumer",
    batch = false,
    offsetReset = OffsetReset.EARLIEST
)
class CreateBookLogConsumer {

    @Topic("create-book-log")
    fun receive(book: Book) {
        Thread.sleep(3000)
        log.info("$CLASS_NAME Consuming of created book $book")
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(CreateBookLogConsumer::class.java)
        private val CLASS_NAME = "[${CreateBookLogConsumer::class.java}]"
    }
}