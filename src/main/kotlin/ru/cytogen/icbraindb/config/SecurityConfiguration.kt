package ru.cytogen.icbraindb.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
class SecurityConfiguration {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.oauth2ResourceServer {
            it.jwt {}
        }.authorizeHttpRequests {
            it.requestMatchers("api/*/save").authenticated()
            it.requestMatchers("api/*/delete/{id}").authenticated()
            it.requestMatchers("api/*/edit").authenticated()
            it.requestMatchers("api/*/metadata").permitAll()
            it.requestMatchers("api/*/{id}").permitAll()
            it.requestMatchers("api/*/").permitAll()
            it.requestMatchers("swagger-ui/**").permitAll()
            it.requestMatchers("v3/api-docs/**").permitAll()
        }.csrf { it.disable() }
        return http.build()
    }
}