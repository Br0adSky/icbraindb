package ru.cytogen.icbraindb.controller

import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.cytogen.icbraindb.dto.request.MutationRequest
import ru.cytogen.icbraindb.dto.request.parsedMutationFilter
import ru.cytogen.icbraindb.dto.request.parsedMutationSort
import ru.cytogen.icbraindb.dto.response.MetadataResponse
import ru.cytogen.icbraindb.dto.response.Response
import ru.cytogen.icbraindb.model.dto.mutation.MutationDto
import ru.cytogen.icbraindb.model.dto.mutation.MutationToEdit
import ru.cytogen.icbraindb.service.mutation.MutationService
import java.util.concurrent.Executors

@Validated
@RestController
@RequestMapping("/api/mutation")
class MutationController(
    private val service: MutationService,
) {
    private val preparedMetadataResponse = MetadataResponse(parsedMutationFilter, parsedMutationSort)
    private val executor = Executors.newSingleThreadExecutor()

    @GetMapping("/{id}")
    fun getById(@PathVariable @NotNull @Positive id: Int?): MutationDto {
        return service.getById(id!!)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable @NotNull @Positive id: Int?) {
        return service.delete(id!!)
    }

    @PostMapping("/")
    fun getMutations(@RequestBody @Valid request: MutationRequest): Response {
        return service.getAll(request)
    }

    @GetMapping("/metadata")
    fun getMetadata(): MetadataResponse {
        return preparedMetadataResponse
    }

    @PostMapping("/edit")
    fun saveEdited(
        @RequestBody
        @Valid request: MutationToEdit
    ) {
        service.saveEdited(request)
    }

    @PostMapping("/save")
    fun saveNew(@RequestBody @Valid request: MutationDto) {
        executor.submit { service.saveNew(request) }.get()
    }
}
