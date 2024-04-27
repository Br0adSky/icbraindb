package ru.cytogen.icbraindb.config

import jakarta.validation.constraints.NotEmpty
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

@Validated
@ConfigurationProperties("client")
class ClientProperties(
    @field:NotEmpty
    val urls: List<String>
) {
}