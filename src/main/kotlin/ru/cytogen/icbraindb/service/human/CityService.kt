package ru.cytogen.icbraindb.service.human

import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.cytogen.icbraindb.exception.CityExistsException
import ru.cytogen.icbraindb.exception.CityNotFound
import ru.cytogen.icbraindb.model.dto.human.CityDto
import ru.cytogen.icbraindb.repository.CityRepository
import java.util.*

@Service
class CityService(
    private val repo: CityRepository
) {
    companion object : KLogging()

    fun getAllAvailable(): List<CityDto> {
        return repo.findAll().map(CityConverter::convertCity)
    }

    fun getExistentCityIdOrThrow(city: CityDto): Long {
        return getCity(city).orElseThrow { CityNotFound(city) }
    }

    fun getExistentCityIdOrCreate(city: CityDto): Long {
        return getCity(city).orElseGet { createCity(city) }
    }

    private fun createCity(city: CityDto): Long {
        val result = repo.save(CityConverter.convertCity(city)).id!!
        logger.info { "Создан новый город: $city с id: $result" }
        return result
    }

    private fun verifyExistsById(id: Long) {
        if (!repo.existsById(id)) {
            throw CityNotFound(id)
        }
    }

    private fun getCity(city: CityDto): Optional<Long> {
        city.id?.let {
            verifyExistsById(city.id)
            return Optional.of(city.id)
        }

        return repo.findByCityNameAndDistrictNameAndCountryCode(
            city.city,
            city.district,
            city.country!!
        ).map { it.getId() }
    }

    @Transactional
    fun saveEdited(request: CityDto) {
        repo.findById(request.id!!).map {
            verifyCityNotExists(request)
            it.cityName = request.city
            it.districtName = request.district
            it.countryCode = request.country!!
            logger.info { "Город с id: ${request.id} обновлен: $request" }
        }.orElseThrow { CityNotFound(request) }

    }

    fun verifyCityNotExists(request: CityDto) {
        if (repo.existsByCityNameAndDistrictNameAndCountryCode(
                request.city,
                request.district,
                request.country!!
            )
        ) {
            throw CityExistsException(request)
        }
    }

    @Transactional
    fun getById(id: Long): CityDto =
        repo.findById(id)
            .map { CityConverter.convertCity(it) }
            .orElseThrow { CityNotFound(id) }

    fun delete(id: Long) {
        repo.deleteById(id)
        logger.info { "Удален город с id: $id" }
    }
}