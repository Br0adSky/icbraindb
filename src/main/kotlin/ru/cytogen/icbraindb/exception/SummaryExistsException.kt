package ru.cytogen.icbraindb.exception

class SummaryExistsException(alias: String) : AlreadyExistsException("Summary by alias: $alias already exists") {
}