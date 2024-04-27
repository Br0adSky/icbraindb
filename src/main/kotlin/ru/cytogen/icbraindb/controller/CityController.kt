package ru.cytogen.icbraindb.controller

import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.cytogen.icbraindb.config.validation.EditAbstractIdValidation
import ru.cytogen.icbraindb.model.dto.human.CityDto
import ru.cytogen.icbraindb.service.human.CityService
import ru.cytogen.icbraindb.service.human.HumanService

@Validated
@RequestMapping("/api/city")
@RestController
class CityController(
    private val service: CityService,
    private val humanService: HumanService
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable @NotNull @Positive id: Long?): CityDto {
        return service.getById(id!!)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable @NotNull @Positive id: Long?) {
        return humanService.deleteCity(id!!)
    }

    @GetMapping("/")
    fun getAvailable(): List<CityDto> {
        return service.getAllAvailable()
    }

    @PostMapping("/edit")
    fun saveEdited(
        @RequestBody
        @Valid
        @EditAbstractIdValidation
        request: CityDto
    ) {
        service.saveEdited(request)
    }
}
