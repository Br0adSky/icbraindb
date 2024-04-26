package ru.cytogen.icbraindb.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration(proxyBeanMethods = false)
class CorsConfigurer(
    private val properties: ClientProperties
) {

    fun createPublicConfiguration() = CorsConfiguration().apply {
        allowedOrigins = listOf("*")
        allowedMethods = listOf("GET", "POST")
    }

    fun createSecuredConfiguration() = CorsConfiguration().apply {
        allowedOrigins = listOf(properties.url)
        allowedMethods = listOf("GET", "POST", "DELETE")
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = createPublicConfiguration()
        val secConfiguration = createSecuredConfiguration()
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("api/*/metadata", configuration)
        source.registerCorsConfiguration("api/*/available", configuration)
        source.registerCorsConfiguration("api/*/{id}", configuration)
        source.registerCorsConfiguration("api/*/", configuration)
        source.registerCorsConfiguration("api/*/delete/{id}", secConfiguration)
        source.registerCorsConfiguration("api/*/save", secConfiguration)
        source.registerCorsConfiguration("api/*/edit", secConfiguration)
        return source
    }
}