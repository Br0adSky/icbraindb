package ru.cytogen.icbraindb.controller

import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.cytogen.icbraindb.config.validation.EditAbstractIdValidation
import ru.cytogen.icbraindb.model.dto.mutation.GeneDto
import ru.cytogen.icbraindb.service.mutation.GeneService

@Validated
@RestController
@RequestMapping("/api/gene")
class GeneController(
    private val service: GeneService
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable @NotNull @Positive id: Long?): GeneDto {
        return service.getById(id!!)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable @NotNull @Positive id: Long?) {
        service.delete(id!!)
    }

    @PostMapping("/edit")
    fun saveEdited(@RequestBody @EditAbstractIdValidation @Valid request: GeneDto) {
        service.saveEdited(request)
    }
}
