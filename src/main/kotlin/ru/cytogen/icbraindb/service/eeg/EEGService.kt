package ru.cytogen.icbraindb.service.eeg

import mu.KLogging
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.cytogen.icbraindb.dto.request.EEGRequest
import ru.cytogen.icbraindb.dto.response.Response
import ru.cytogen.icbraindb.exception.EEGNotFound
import ru.cytogen.icbraindb.filter.EEGFilter
import ru.cytogen.icbraindb.model.db.eeg.EEGFile
import ru.cytogen.icbraindb.model.dto.eeg.EEGDto
import ru.cytogen.icbraindb.repository.EEGRepository
import ru.cytogen.icbraindb.service.human.HumanService
import ru.cytogen.icbraindb.service.utils.PageConverter

@Service
class EEGService(
    private val repo: EEGRepository,
    private val humanService: HumanService
) {
    companion object : KLogging()

    fun getAll(request: EEGRequest): Response {
        val pagination = PageConverter.of(request)

        val data = request.filter?.let {
            repo.findAll(getSpecification(it), pagination)
        } ?: repo.findAll(pagination)

        return Response.from(request, data, EEGFileConverter::convert)
    }

    @Transactional
    fun saveEdited(request: EEGDto) {
        repo.findById(request.id!!)
            .map {
                it.eegId = request.eegId!!
                it.description = request.description
                it.path = request.path!!
                it.filename = request.fileName!!
                logger.info { "ЭЭГ файл с id: ${request.id} обновлен: $request" }
            }
            .orElseThrow { EEGNotFound(request) }
    }

    @Transactional
    fun saveNew(request: EEGDto) {
        request.human?.let { humanService.verifyHumanExists(it) }

        repo.persist(EEGFileConverter.convert(request))
        logger.info { "Сохранен новый ЭЭГ файл: $request" }
    }

    private fun getSpecification(request: EEGFilter): Specification<EEGFile> {
        return Specification.where(request.eegID?.let(EEGSpecification::eegID))
            .and(request.description?.let(EEGSpecification::description))
            .and(request.human?.let(EEGSpecification::human))
            .and(request.fileName?.let(EEGSpecification::fileName))
    }

    fun getAll(): Iterable<EEGDto> {
        return repo.findAll().map(EEGFileConverter::convert)
    }

    @Transactional
    fun getById(id: Int): EEGDto =
        repo.findById(id)
            .map { EEGFileConverter.convert(it) }
            .orElseThrow { EEGNotFound(id) }

    @Transactional
    fun delete(id: Int) {
        repo.deleteById(id)
        logger.info { "Удален ЭЭГ файл с id: $id" }
    }
}