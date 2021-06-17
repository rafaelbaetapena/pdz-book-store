package com.rafaelbaetapena.adapters.out.persistence.v1.repositories

import com.rafaelbaetapena.adapters.out.persistence.v1.entities.BookEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import java.util.*

@Repository
interface BookRepository : CrudRepository<BookEntity, UUID>