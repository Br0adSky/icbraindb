package ru.cytogen.icbraindb.model.dto

interface AbstractIdDto<T : Any> {
    val id: T?
}