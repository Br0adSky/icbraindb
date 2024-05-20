package ru.cytogen.icbraindb.exception

class UndefinedLocaleException(locale: String) : RuntimeException("Received unknown locale: $locale") {
}