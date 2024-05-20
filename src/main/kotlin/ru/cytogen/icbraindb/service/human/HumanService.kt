package ru.cytogen.icbraindb.service.human

import mu.KLogging
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.cytogen.icbraindb.dto.request.HumanRequest
import ru.cytogen.icbraindb.dto.response.Response
import ru.cytogen.icbraindb.exception.HumanExistsException
import ru.cytogen.icbraindb.exception.HumanNotExistsException
import ru.cytogen.icbraindb.filter.HumanFilter
import ru.cytogen.icbraindb.model.db.human.Human
import ru.cytogen.icbraindb.model.dto.human.HumanDto
import ru.cytogen.icbraindb.repository.HumanDeleteRepository
import ru.cytogen.icbraindb.repository.HumanRepository
import ru.cytogen.icbraindb.repository.HumanWriteRepository
import ru.cytogen.icbraindb.service.LocaleTypes
import ru.cytogen.icbraindb.service.utils.PageConverter

@Service
class HumanService(
    private val repo: HumanRepository,
    private val toSaveRepo: HumanWriteRepository,
    private val nationalityService: NationalityService,
    private val diseaseService: DiseaseService,
    private val cityService: CityService,
    private val deleteRepo: HumanDeleteRepository
) {
    companion object : KLogging()

    @Transactional
    fun getById(id: String): HumanDto {
        return repo.findById(id).map(HumanConverter::convert).orElseThrow { HumanNotExistsException(id) }
    }

    @Transactional
    fun getAll(request: HumanRequest, locale: LocaleTypes): Response {
        val pagination = PageConverter.of(request)
        val data = request.filter?.let {
            repo.findAll(getSpecification(it, locale), pagination)
        } ?: repo.findAll(pagination)

        return Response.from(request, data, HumanConverter::convert)
    }

    private fun getSpecification(filter: HumanFilter, locale: LocaleTypes): Specification<Human> {
        return Specification.where(filter.age?.let(HumanSpecification::age))
            .and(filter.district?.let(HumanSpecification::district))
            .and(filter.city?.let(HumanSpecification::city))
            .and(filter.country?.let(HumanSpecification::country))
            .and(filter.id?.let(HumanSpecification::id))
            .and(filter.ethnos?.let(HumanSpecification::ethnos))
            .and(filter.hasMutations?.let(HumanSpecification::hasMutation))
            .and(filter.hasSummaries?.let(HumanSpecification::hasSummaries))
            .and(filter.hasEEGFiles?.let(HumanSpecification::hasEEGFiles))
            .and(filter.sex?.let(HumanSpecification::sex))
            .and(filter.migrant?.let(HumanSpecification::isMigrant))
            .and(filter.nationalities?.takeIf { it.value.isNotEmpty() }?.let {
                HumanSpecification.nationalities(it, locale)
            })
            .and(filter.diseases?.takeIf { it.value.isNotEmpty() }?.let(HumanSpecification::diseases))
    }

    @Transactional
    fun getAll(): Iterable<HumanDto> {
        return repo.findAll().map(HumanConverter::convert)
    }

    @Transactional
    fun saveEdited(request: HumanDto) {
        verifyHumanExists(request.id!!)
        toSaveRepo.merge(
            HumanConverter.convert(
                request,
                request.city?.let(cityService::getExistentCityIdOrThrow),
                nationalityService.getExistentNationalityIds(request.nationalities),
                diseaseService.getExistentDiseaseIds(request.diseases)
            )
        )
        logger.info { "Человек с id: ${request.id} обновлен: $request" }
    }

    @Transactional
    fun saveNew(request: HumanDto) {
        if (toSaveRepo.existsById(request.id!!)) {
            throw HumanExistsException(request.id)
        }

        val nationIds = nationalityService.getExistentNationalityIdsOrCreate(request.nationalities)
        val diseases = diseaseService.getExistentOrCreate(request.diseases)
        val city = request.city?.let(cityService::getExistentCityIdOrCreate)
        toSaveRepo.persist(HumanConverter.convert(request, city, nationIds, diseases))
        logger.info { "Сохранен новый человек: $request" }
    }

    fun verifyHumanExists(humanID: String) {
        if (!toSaveRepo.existsById(humanID)) {
            throw HumanNotExistsException(humanID)
        }
    }

    @Transactional
    fun delete(id: String) {
        //чтобы заблочить и обновление в saveEdited встало на паузу
        deleteRepo.findById(id).ifPresent { deleteRepo.delete(it) }
        logger.info { "Удален человек с id: $id" }
    }

    @Transactional
    fun deleteCity(cityId: Long) {
        repo.executeUpdate(HumanSpecification.deleteCity(cityId))
        cityService.delete(cityId)
    }
}
