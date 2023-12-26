package ru.cytogen.icbraindb

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import ru.cytogen.icbraindb.dto.HumanDto
import ru.cytogen.icbraindb.dto.MutationDto
import ru.cytogen.icbraindb.model.Human
import ru.cytogen.icbraindb.model.Mutation
import ru.cytogen.icbraindb.repository.HumanRepository
import ru.cytogen.icbraindb.repository.MutationRepository
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

//@Component
class HumansInitializer(
    private val repository: MutationRepository,
    private val humanRepository: HumanRepository,
    private val mapper: ObjectMapper
) {
    @PostConstruct
    fun process() {
//        processHumans()
//        processMutation()

    }

    fun processHumans() {
        val result = mapper.readValue(
            File("src/test/kotlin/ru/cytogen/icbraindb/2023-11-06T165619.200.json"),
            object : TypeReference<List<HumanDto>>() {})

        humanRepository.saveAll(result.map {
            Human(
                it.id,
                it.age,
                it.comments,
                it.ethos,
                it.nationality,
                it.nationalityEn,
                it.city,
                it.country,
                it.district,
                it.sex
            )
        })
    }

    fun processMutation() {
        val result = mapper.readValue(
            File(".idea/httpRequests/2023-11-06T183043.200.json"),
            object : TypeReference<List<MutationDto>>() {})

        val saved = repository.saveAll(result.map {
            Mutation(
                it.id,
                it.chromosome,
                it.gene,
                humanRepository.findById(it.human).get(),
                it.mutation,
                it.position,
                it.referenceNucleotide,
                it.type
            )
        })
        print(saved)
    }
}