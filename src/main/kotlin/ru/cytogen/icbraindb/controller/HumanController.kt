package ru.cytogen.icbraindb.controller

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.cytogen.icbraindb.dto.request.HumanRequest
import ru.cytogen.icbraindb.dto.request.parsedHumanFilter
import ru.cytogen.icbraindb.dto.request.parsedHumanSort
import ru.cytogen.icbraindb.dto.response.MetadataResponse
import ru.cytogen.icbraindb.dto.response.Response
import ru.cytogen.icbraindb.model.dto.human.HumanDto
import ru.cytogen.icbraindb.service.human.HumanService

@Validated
@RestController
@RequestMapping("/api/human")
class HumanController(
    private val service: HumanService
) {
    private val preparedMetadataResponse = MetadataResponse(parsedHumanFilter, parsedHumanSort)

    @DeleteMapping("/delete/{id}")
    fun deleteHuman(@PathVariable @NotBlank id: String?) {
        service.delete(id!!)
    }

    @GetMapping("/{id}")
    fun getHuman(@PathVariable @NotBlank id: String?): HumanDto {
        return service.getById(id!!)
    }

    @GetMapping("/")
    fun getAllHumans(): Iterable<HumanDto> {
        return service.getAll()
    }

    @PostMapping("/")
    fun getHumans(@RequestBody @Valid request: HumanRequest): Response {
        return service.getAll(request)
    }

    @GetMapping("/metadata")
    fun getMetadata(): MetadataResponse {
        return preparedMetadataResponse
    }

    @PostMapping("/edit")
    fun saveEdited(@RequestBody @Valid request: HumanDto) {
        service.saveEdited(request)
    }

    @PostMapping("/save")
    fun saveNew(@RequestBody @Valid request: HumanDto) {
        service.saveNew(request)
    }
}
