package com.rafaelbaetapena.adapters.out.kafka.v1

import com.rafaelbaetapena.application.domain.Book
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic
import java.util.*

@KafkaClient(
    id = "create-book-log-producer"
)
interface CreateBookLogProducer {

    @Topic("create-book-log")
    fun send(@KafkaKey bookId: UUID, book: Book)
}