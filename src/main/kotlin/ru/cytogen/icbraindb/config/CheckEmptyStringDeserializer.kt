package ru.cytogen.icbraindb.config

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StringDeserializer


class CheckEmptyStringDeserializer : StringDeserializer() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): String? {
        val value = super.deserialize(p, ctxt)
        return value?.takeIf { it.isNotBlank() }
    }
}