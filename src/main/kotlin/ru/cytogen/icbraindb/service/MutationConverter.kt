package ru.cytogen.icbraindb.service

import ru.cytogen.icbraindb.dto.MutationDto
import ru.cytogen.icbraindb.model.Mutation

object MutationConverter {
  fun convert(mutation: Mutation): MutationDto {
    return MutationDto(
        mutation.gene.chromosome,
        mutation.gene.geneName,
        mutation.mutation,
        mutation.position,
        mutation.referenceNucleotide,
        mutation.type.getDescription()
    )
  }
}
