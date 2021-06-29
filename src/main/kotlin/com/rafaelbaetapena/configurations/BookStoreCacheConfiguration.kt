package com.rafaelbaetapena.configurations

import io.micronaut.context.annotation.ConfigurationProperties
import org.slf4j.LoggerFactory
import javax.annotation.PostConstruct

@ConfigurationProperties("book-store.cache")
class BookStoreCacheConfiguration{

    var timeOutSeconds: Long = 0L

    @PostConstruct
    fun postConstructor(){
        TIME_OUT_SECONDS = timeOutSeconds
        LOG.info("$CLASS_NAME Time out seconds value is $timeOutSeconds")
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(BookStoreCacheConfiguration::class.java)
        private val CLASS_NAME = "[${BookStoreCacheConfiguration::class.java}]"
        var TIME_OUT_SECONDS: Long = 0L
    }
}