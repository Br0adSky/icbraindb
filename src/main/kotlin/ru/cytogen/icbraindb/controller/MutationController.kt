package ru.cytogen.icbraindb.controller

import jakarta.validation.Valid
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import ru.cytogen.icbraindb.dto.MutationSortColumn
import ru.cytogen.icbraindb.dto.request.Request
import ru.cytogen.icbraindb.dto.response.Response
import ru.cytogen.icbraindb.filter.MutationFilter
import ru.cytogen.icbraindb.service.MutationService

@RestController
@RequestMapping("/api/mutation")
@Transactional
class MutationController(
    private val service: MutationService
) {
  @PostMapping("/")
  fun getHumans(@RequestBody @Valid request: Request<MutationFilter, MutationSortColumn>): Response<MutationFilter, MutationSortColumn> {
    return service.getAll(request)
  }
}
