package ru.cytogen.icbraindb.controller

import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.cytogen.icbraindb.config.validation.EditAbstractIdValidation
import ru.cytogen.icbraindb.config.validation.nationality.NationalityToSaveValidation
import ru.cytogen.icbraindb.model.dto.human.NationalityDto
import ru.cytogen.icbraindb.service.human.NationalityService

@Validated
@RequestMapping("/api/nationality")
@RestController
class NationalityController(
    private val service: NationalityService
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable @NotNull @Positive id: Long?): NationalityDto {
        return service.getById(id!!)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable @NotNull @Positive id: Long?) {
        return service.delete(id!!)
    }

    @GetMapping("/")
    fun getAvailable(): List<NationalityDto> {
        return service.getAllAvailable()
    }

    @PostMapping("/edit")
    fun saveEdited(
        @RequestBody
        @Valid
        @EditAbstractIdValidation
        @NationalityToSaveValidation
        request: NationalityDto
    ) {
        service.saveEdited(request)
    }
}
