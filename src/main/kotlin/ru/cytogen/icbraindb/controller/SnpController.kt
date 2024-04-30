package ru.cytogen.icbraindb.controller

import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.cytogen.icbraindb.model.dto.mutation.SnpDto
import ru.cytogen.icbraindb.model.dto.mutation.SnpToEdit
import ru.cytogen.icbraindb.service.mutation.SnpService

@Validated
@RestController
@RequestMapping("/api/snp")
class SnpController(
    private val service: SnpService
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable @NotNull @Positive id: Long?): SnpDto {
        return service.getById(id!!)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable @NotNull @Positive id: Long?) {
        return service.delete(id!!)
    }

    @PostMapping("/edit")
    fun saveEdited(@RequestBody @Valid request: SnpToEdit) {
        service.saveEdited(request)
    }
}
