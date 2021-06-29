package com.rafaelbaetapena.configurations

import io.micronaut.context.annotation.ConfigurationProperties
import org.slf4j.LoggerFactory
import javax.annotation.PostConstruct
import javax.validation.constraints.NotNull

@ConfigurationProperties("book-store.log")
class BookStoreLogConfiguration{

    var indexPrefix: String = ""

    @PostConstruct
    fun postConstructor(){
        INDEX_PREFIX = indexPrefix
        LOG.info("$CLASS_NAME Index prefix value is $indexPrefix")
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(BookStoreLogConfiguration::class.java)
        private val CLASS_NAME = "[${BookStoreLogConfiguration::class.java}]"
        var INDEX_PREFIX: String = ""
    }
}