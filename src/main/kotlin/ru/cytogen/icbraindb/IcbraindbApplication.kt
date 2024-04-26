package ru.cytogen.icbraindb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
@ConfigurationPropertiesScan
class IcbraindbApplication

fun main(args: Array<String>) {
    Locale.setDefault(Locale.ENGLISH)
    runApplication<IcbraindbApplication>(*args)
}