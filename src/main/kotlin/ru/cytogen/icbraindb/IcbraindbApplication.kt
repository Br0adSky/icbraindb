package ru.cytogen.icbraindb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IcbraindbApplication

fun main(args: Array<String>) {
    runApplication<IcbraindbApplication>(*args)
}