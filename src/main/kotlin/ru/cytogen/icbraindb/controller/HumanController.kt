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
import java.util.concurrent.Executors

@Validated
@RestController
@RequestMapping("/api/human")
class HumanController(
    private val service: HumanService
) {
    private val preparedMetadataResponse = MetadataResponse(parsedHumanFilter, parsedHumanSort)
    private val executor = Executors.newSingleThreadExecutor()

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
        /* TODO
        * это не масштабируется горизонтально, если хочется замасштабировать, то придется добавлять что-то типа очереди -- Redis и тп
        * чтобы вычитывать и обрабатывать по одному, и чтобы ошибки возвращать на клиент -- поднимать вебсокет. Вообще тут и так веб сокет нужен,
        * есть вопросики как оно будет работать, если 10_000 записей загрузить с клиента.
        * Ну либо просто убрать такую фичу как каскадное создание в случае отсутствия, хотя для мутаций будет грустно конечно.
        * */
        executor.submit { service.saveNew(request) }.get()
    }
}
