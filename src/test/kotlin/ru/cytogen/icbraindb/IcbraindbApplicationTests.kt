package ru.cytogen.icbraindb

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import ru.cytogen.icbraindb.dto.HumanDto
import java.io.File

class IcbraindbApplicationTests {

    @Test
    fun contextLoads() {
        val mapper = ObjectMapper()
        val result = mapper.readValue(File("src/test/kotlin/ru/cytogen/icbraindb/2023-11-06T165619.200.json"),
            object : TypeReference<List<HumanDto>>() {})
        print(result)

    }

}
