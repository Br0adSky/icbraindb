package ru.cytogen.icbraindb.controller

import jakarta.validation.Valid
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import ru.cytogen.icbraindb.dto.HumanDto
import ru.cytogen.icbraindb.dto.HumanSortColumn
import ru.cytogen.icbraindb.dto.request.Request
import ru.cytogen.icbraindb.dto.response.Response
import ru.cytogen.icbraindb.filter.HumanFilter
import ru.cytogen.icbraindb.service.HumanService

@RestController
@RequestMapping("/api/human")
@Transactional
class HumanController(
    private val service: HumanService
) {
  @GetMapping("/")
  fun getAllHumans(): Iterable<HumanDto> {
    return service.getAll()
  }

  @PostMapping("/")
  fun getHumans(@RequestBody @Valid request: Request<HumanFilter, HumanSortColumn>): Response<HumanFilter, HumanSortColumn> {
    return service.getAll(request)
  }
}
