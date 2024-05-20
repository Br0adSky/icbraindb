package ru.cytogen.icbraindb.service

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import ru.cytogen.icbraindb.exception.UndefinedLocaleException

enum class LocaleTypes {
    RU,
    EN
}

@Component
class LocaleConverter : Converter<String, LocaleTypes> {
    override fun convert(source: String): LocaleTypes {
        return LocaleTypes.values().find { it.name == source } ?: throw UndefinedLocaleException(source)
    }
}