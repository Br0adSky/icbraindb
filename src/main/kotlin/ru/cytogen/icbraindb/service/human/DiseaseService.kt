package ru.cytogen.icbraindb.service.human

import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.cytogen.icbraindb.exception.DiseaseExists
import ru.cytogen.icbraindb.exception.DiseaseNotFound
import ru.cytogen.icbraindb.model.dto.human.DiseaseDto
import ru.cytogen.icbraindb.repository.DiseaseRepository
import java.util.*

@Service
class DiseaseService(
    private val repo: DiseaseRepository
) {
    companion object : KLogging()

    fun getAllAvailable(): List<DiseaseDto> {
        return repo.findAll().map(HumanConverter::convertDisease)
    }

    @Transactional
    fun getExistentOrCreate(disease: List<DiseaseDto>): Set<Long> {
        return disease.asSequence().map(::getExistentDiseaseIdOrCreate).toSet()
    }

    fun getExistentDiseaseIds(disease: List<DiseaseDto>): Set<Long> {
        return disease.asSequence().map(::getExistentDiseaseIdOrThrow).toSet()
    }

    private fun verifyExistsById(id: Long) {
        if (!repo.existsById(id)) {
            throw DiseaseNotFound(id)
        }
    }

    private fun getExistentDiseaseId(disease: DiseaseDto): Optional<Long> {
        disease.id?.let {
            verifyExistsById(it)
            return Optional.of(it)
        }

        return repo.findByDisease(disease.disease!!).map { it.getId() }
    }

    private fun getExistentDiseaseIdOrCreate(disease: DiseaseDto): Long {
        return getExistentDiseaseId(disease).orElseGet { createNew(disease) }
    }

    private fun createNew(disease: DiseaseDto): Long {
        val result = repo.save(HumanConverter.convertDisease(disease)).id!!
        logger.info { "Создана новая болезнь: $disease с id: $result" }
        return result
    }

    private fun getExistentDiseaseIdOrThrow(disease: DiseaseDto): Long {
        return getExistentDiseaseId(disease).orElseThrow { DiseaseNotFound(disease) }
    }

    @Transactional
    fun saveEdited(request: DiseaseDto) {
        repo.findById(request.id!!).map {
            verifyDiseaseNotExists(request)
            it.disease = request.disease!!
            logger.info { "Болезнь с id: ${request.id} обновлена: $request" }
        }.orElseThrow { DiseaseNotFound(request) }
    }

    fun verifyDiseaseNotExists(request: DiseaseDto) {
        if (repo.existsByDisease(request.disease!!)) {
            throw DiseaseExists(request)
        }
    }

    @Transactional
    fun delete(id: Long) {
        repo.deleteById(id)
        logger.info { "Удалена болезнь с id: $id" }
    }

    @Transactional
    fun getById(id: Long): DiseaseDto =
        repo.findById(id)
            .map { HumanConverter.convertDisease(it) }
            .orElseThrow { DiseaseNotFound(id) }
}