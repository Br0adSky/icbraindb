package ru.cytogen.icbraindb.exception

open class AlreadyExistsException(override val message: String): RuntimeException(message) {
}