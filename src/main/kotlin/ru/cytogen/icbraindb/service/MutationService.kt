package ru.cytogen.icbraindb.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import ru.cytogen.icbraindb.dto.MutationSortColumn
import ru.cytogen.icbraindb.dto.common.Sort
import ru.cytogen.icbraindb.dto.common.SortType
import ru.cytogen.icbraindb.dto.request.PageRequestDto
import ru.cytogen.icbraindb.dto.request.Request
import ru.cytogen.icbraindb.dto.response.PageResponseDto
import ru.cytogen.icbraindb.dto.response.Response
import ru.cytogen.icbraindb.filter.MutationFilter
import ru.cytogen.icbraindb.filter.service.FilterParser
import ru.cytogen.icbraindb.model.Mutation
import ru.cytogen.icbraindb.repository.MutationRepository

typealias MutationSort = Sort<MutationSortColumn>

@Service
class MutationService(
    private val repo: MutationRepository
) {

  fun getAll(request: Request<MutationFilter, MutationSortColumn>): Response<MutationFilter, MutationSortColumn> {
    val page = request.page ?: PageRequestDto(0, 10)
    val sort = request.sort ?: MutationSort(MutationSortColumn.CHROMOSOME, SortType.ASC)
    val data = repo.findAll(getSpecification(request),
        PageRequest.of(page.number, page.size)
            .withSort(SortConverter.convert(sort))
    ).map(MutationConverter::convert)

    return Response(
        data.toList(),
        request.filter,
        PageResponseDto(data.pageable.pageNumber, data.pageable.pageSize, data.totalPages),
        sort,
        FilterParser.getFilterCache(MutationFilter::class)
    )
  }

  private fun getSpecification(request: Request<MutationFilter, MutationSortColumn>): Specification<Mutation> {
    val filter = request.filter ?: return Specification.where(null)
    return Specification.where(filter.mutation?.let(MutationSpecification::mutation))
        .and(filter.types?.let(MutationSpecification::type))
        .and(filter.chromosome?.let(MutationSpecification::chromosome))
        .and(filter.gene?.let(MutationSpecification::gene))
        .and(filter.position?.let(MutationSpecification::position))
        .and(filter.humans?.let(MutationSpecification::humans))
        .and(filter.refNucl?.let(MutationSpecification::refNucl))
  }
}
