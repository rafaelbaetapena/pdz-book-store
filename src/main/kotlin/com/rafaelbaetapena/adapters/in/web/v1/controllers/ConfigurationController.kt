package com.rafaelbaetapena.adapters.`in`.web.v1.controllers

import com.rafaelbaetapena.configurations.BookStoreCacheConfiguration
import com.rafaelbaetapena.configurations.BookStoreLogConfiguration
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import org.slf4j.LoggerFactory

@Controller("/v1/configuration")
class ConfigurationController(
        private val bookStoreLogConfiguration: BookStoreLogConfiguration,
        private val bookStoreCacheConfiguration: BookStoreCacheConfiguration
) {

    @Get
    fun configuration(): HttpResponse<Any> {

        LOG.info("$CLASS_NAME starting configuration")

        val logConfiguration = object {
            val indexPrefix = bookStoreLogConfiguration.indexPrefix
        }

        val cacheConfiguration = object {
            val timeOutSeconds = bookStoreCacheConfiguration.timeOutSeconds
        }

        LOG.info("$CLASS_NAME finalized configuration")

        return HttpResponse.ok(object {
            val logConfiguration = logConfiguration
            val cacheConfiguration = cacheConfiguration
        })
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(ConfigurationController::class.java)
        private val CLASS_NAME = "[${ConfigurationController::class.java}]"
    }
}