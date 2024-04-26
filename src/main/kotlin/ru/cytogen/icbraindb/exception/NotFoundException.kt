package ru.cytogen.icbraindb.exception

open class NotFoundException(override val message: String) : RuntimeException(message) {
}