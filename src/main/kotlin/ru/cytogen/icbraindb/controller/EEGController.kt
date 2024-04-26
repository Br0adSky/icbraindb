package ru.cytogen.icbraindb.controller

import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.cytogen.icbraindb.config.validation.EditAbstractIdValidation
import ru.cytogen.icbraindb.dto.request.EEGRequest
import ru.cytogen.icbraindb.dto.request.parsedEEGFilter
import ru.cytogen.icbraindb.dto.request.parsedEEGSort
import ru.cytogen.icbraindb.dto.response.MetadataResponse
import ru.cytogen.icbraindb.dto.response.Response
import ru.cytogen.icbraindb.model.dto.eeg.EEGDto
import ru.cytogen.icbraindb.service.eeg.EEGService

@Validated
@RestController
@RequestMapping("/api/eeg")
class EEGController(
    private val service: EEGService
) {
    private val preparedMetadataResponse = MetadataResponse(parsedEEGFilter, parsedEEGSort)

    @GetMapping("/{id}")
    fun getById(@PathVariable @NotNull @Positive id: Int?): EEGDto {
        return service.getById(id!!)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable @NotNull @Positive id: Int?) {
        return service.delete(id!!)
    }

    @GetMapping("/")
    fun getAllEEG(): Iterable<EEGDto> {
        return service.getAll()
    }

    @PostMapping("/")
    fun getEEG(@RequestBody @Valid request: EEGRequest): Response {
        return service.getAll(request)
    }

    @GetMapping("/metadata")
    fun getMetadata(): MetadataResponse {
        return preparedMetadataResponse
    }

    @PostMapping("/edit")
    fun saveEdited(@RequestBody @Valid @EditAbstractIdValidation request: EEGDto) {
        service.saveEdited(request)
    }

    @PostMapping("/save")
    fun saveNew(@RequestBody @Valid request: EEGDto) {
        service.saveNew(request)
    }
}