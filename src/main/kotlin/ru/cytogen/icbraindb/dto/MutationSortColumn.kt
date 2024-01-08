package ru.cytogen.icbraindb.dto

import org.springframework.data.domain.Sort
import ru.cytogen.icbraindb.dto.common.SortColumn
import ru.cytogen.icbraindb.model.Gene_
import ru.cytogen.icbraindb.model.Mutation_

enum class MutationSortColumn(private val sort: Sort) : SortColumn {
  TYPE(Sort.by(Mutation_.TYPE)),
  REF_NUCL(Sort.by(Mutation_.REFERENCE_NUCLEOTIDE)),
  POSITION(Sort.by(Mutation_.POSITION)),
  MUTATION(Sort.by(Mutation_.MUTATION)),
  GENE(Sort.by("${Mutation_.GENE}.${Gene_.GENE_NAME}")),
  CHROMOSOME(Sort.by("${Mutation_.GENE}.${Gene_.CHROMOSOME}"));

  override fun getSort(): Sort {
    return sort
  }
}
