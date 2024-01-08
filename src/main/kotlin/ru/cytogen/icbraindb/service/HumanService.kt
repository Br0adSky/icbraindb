package ru.cytogen.icbraindb.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import ru.cytogen.icbraindb.dto.HumanDto
import ru.cytogen.icbraindb.dto.HumanSortColumn
import ru.cytogen.icbraindb.dto.common.SortType
import ru.cytogen.icbraindb.dto.request.PageRequestDto
import ru.cytogen.icbraindb.dto.request.Request
import ru.cytogen.icbraindb.dto.response.PageResponseDto
import ru.cytogen.icbraindb.dto.response.Response
import ru.cytogen.icbraindb.filter.HumanFilter
import ru.cytogen.icbraindb.filter.service.FilterParser
import ru.cytogen.icbraindb.model.Human
import ru.cytogen.icbraindb.repository.HumanRepository

typealias HumanSort = ru.cytogen.icbraindb.dto.common.Sort<HumanSortColumn>

@Service
class HumanService(
    private val repo: HumanRepository
) {
  fun getAll(request: Request<HumanFilter, HumanSortColumn>): Response<HumanFilter, HumanSortColumn> {
    val page = request.page ?: PageRequestDto(0, 10)
    val sort = request.sort ?: HumanSort(HumanSortColumn.ID, SortType.ASC)
    val data = repo.findAll(getSpecification(request),
        PageRequest.of(page.number, page.size)
            .withSort(SortConverter.convert(sort))
    ).map(HumanConverter::convert)

    return Response(
        data.toList(),
        request.filter,
        PageResponseDto(data.pageable.pageNumber, data.pageable.pageSize, data.totalPages),
        sort,
        FilterParser.getFilterCache(HumanFilter::class)
    )
  }

  private fun getSpecification(request: Request<HumanFilter, HumanSortColumn>): Specification<Human> {
    val filter = request.filter ?: return Specification.where(null)
    return Specification.where(filter.age?.let(HumanSpecification::age))
        .and(filter.district?.let(HumanSpecification::district))
        .and(filter.city?.let(HumanSpecification::city))
        .and(filter.country?.let(HumanSpecification::country))
        .and(filter.id?.let(HumanSpecification::id))
        .and(filter.ethnos?.let(HumanSpecification::ethnos))
        .and(filter.hasDiseases?.let(HumanSpecification::hasDiseases))
        .and(filter.hasMutations?.let(HumanSpecification::hasMutation))
        .and(filter.hasSummaries?.let(HumanSpecification::hasSummaries))
        .and(filter.hasEEGFiles?.let(HumanSpecification::hasEEGFiles))
        .and(filter.sex?.let(HumanSpecification::sex))
        .and(filter.migrant?.let(HumanSpecification::isMigrant))
        .and(filter.nationalities?.let(HumanSpecification::nationalities))
  }

  fun getAll(): Iterable<HumanDto> {
    return repo.findAll().map(HumanConverter::convert)
  }
}
