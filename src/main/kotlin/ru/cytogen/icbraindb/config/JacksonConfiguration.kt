package ru.cytogen.icbraindb.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration(proxyBeanMethods = false)
class JacksonConfiguration {
    @Bean
    @Primary
    fun objectMapper(): ObjectMapper {
        val module = SimpleModule().apply {
            addDeserializer(String::class.java, CheckEmptyStringDeserializer())
        }
        return ObjectMapper().apply {
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
            registerModule(module)
            registerModule(kotlinModule())
        }
    }
}
