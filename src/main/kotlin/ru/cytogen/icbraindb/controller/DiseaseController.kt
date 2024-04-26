package ru.cytogen.icbraindb.controller

import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.cytogen.icbraindb.config.validation.EditAbstractIdValidation
import ru.cytogen.icbraindb.model.dto.human.DiseaseDto
import ru.cytogen.icbraindb.service.human.DiseaseService

@Validated
@RequestMapping("/api/disease")
@RestController
class DiseaseController(
    private val service: DiseaseService
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable @NotNull @Positive id: Long?): DiseaseDto {
        return service.getById(id!!)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable @NotNull @Positive id: Long?) {
        return service.delete(id!!)
    }

    @GetMapping("/available")
    fun getAvailable(): List<DiseaseDto> {
        return service.getAllAvailable()
    }

    @PostMapping("/edit")
    fun saveEdited(
        @RequestBody
        @Valid
        @EditAbstractIdValidation
        request: DiseaseDto
    ) {
        service.saveEdited(request)
    }
}
