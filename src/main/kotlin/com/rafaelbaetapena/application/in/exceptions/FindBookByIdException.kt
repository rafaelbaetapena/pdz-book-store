package com.rafaelbaetapena.application.`in`.exceptions

class FindBookByIdException(override val message: String = "No book was found for the Id entered"):
        Exception(message) {

}