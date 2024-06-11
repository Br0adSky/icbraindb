package ru.cytogen.icbraindb.service.human

import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.cytogen.icbraindb.exception.NationalityExistsException
import ru.cytogen.icbraindb.exception.NationalityNotFound
import ru.cytogen.icbraindb.model.dto.human.NationalityDto
import ru.cytogen.icbraindb.repository.NationalityRepository
import java.util.*

@Service
class NationalityService(
    private val repo: NationalityRepository
) {
    companion object : KLogging()

    fun getAllAvailable(): List<NationalityDto> {
        return repo.findAll().map(NationalityConverter::convertNationality)
    }

    @Transactional
    fun saveEdited(request: NationalityDto) {
        repo.findById(request.id!!).map {
            verifyNationalityNotExists(request)
            it.nationality = request.nationality
            it.nationalityEN = request.nationalityEn
            logger.info { "Национальность с id: ${request.id} обновлена: $request" }
        }.orElseThrow { NationalityNotFound(request) }

    }

    fun verifyNationalityNotExists(request: NationalityDto) {
        request.nationality?.let {
            if (repo.existsByNationality(it)) {
                throw NationalityExistsException(request)
            }
        }
        request.nationalityEn?.let {
            if (repo.existsByNationalityEN(it)) {
                throw NationalityExistsException(request)
            }
        }
    }

    fun getExistentNationalityIds(nationalities: List<NationalityDto>): Set<Long> {
        return nationalities.asSequence().map(::getExistentNationalityIdOrThrow).toSet()
    }

    @Transactional
    fun getExistentNationalityIdsOrCreate(nationalities: List<NationalityDto>): Set<Long> {
        return nationalities.asSequence().map(::getExistentNationalityIdOrCreate).toSet()
    }

    private fun verifyExistsById(id: Long) {
        if (!repo.existsById(id)) {
            throw NationalityNotFound(id)
        }
    }

    private fun getExistentNationalityId(nationality: NationalityDto): Optional<Long> {
        nationality.id?.let {
            verifyExistsById(nationality.id)
            return Optional.of(nationality.id)
        }

        nationality.nationality?.let {
            val byNameOpt = repo.findByNationality(it)
            if (byNameOpt.isPresent) {
                return byNameOpt.map { it.getId() }
            }
        }
        return repo.findByNationalityEN(nationality.nationalityEn!!).map { it.getId() }
    }

    private fun getExistentNationalityIdOrCreate(nationality: NationalityDto): Long {
        return getExistentNationalityId(nationality).orElseGet {
            createNationality(nationality)
        }
    }

    private fun createNationality(nationality: NationalityDto): Long {
        val result = repo.save(NationalityConverter.convertNationality(
            nationality
        )).id!!
        logger.info { "Создана новая национальность: $nationality с id: $result" }
        return result
    }

    private fun getExistentNationalityIdOrThrow(nationality: NationalityDto): Long {
        return getExistentNationalityId(nationality).orElseThrow { NationalityNotFound(nationality) }
    }

    @Transactional
    fun getById(id: Long): NationalityDto {
        return repo.findById(id)
            .map { NationalityConverter.convertNationality(it) }
            .orElseThrow { NationalityNotFound(id) }
    }

    @Transactional
    fun delete(id: Long) {
//        val builder = manager.criteriaBuilder
//        val query = builder.createCriteriaDelete(Nationality::class.java)
//        val root = query.root
//        query.where(builder.equal(root.get(Nationality_.id), id))
//        manager.createQuery(query).executeUpdate()
        //TODO: удаление ниже производит один доп. селект, чтобы понять есть че или нет. Полезность такого -- если ниче нет, не будем пробовать остальные запросы,
        //код выше же сразу выполнит 2 запроса с удалением.
        repo.deleteById(id)
        logger.info { "Удалена национальность с id: $id" }
    }
}