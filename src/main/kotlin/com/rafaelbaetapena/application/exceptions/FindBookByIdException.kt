package com.rafaelbaetapena.application.exceptions

class FindBookByIdException(override val message: String = "No book was found for the Id entered"):
        Exception(message)