package ru.cytogen.icbraindb.service.mutation

import ru.cytogen.icbraindb.model.db.mutation.*
import ru.cytogen.icbraindb.model.dto.mutation.GeneDto
import ru.cytogen.icbraindb.model.dto.mutation.MutationDto
import ru.cytogen.icbraindb.model.dto.mutation.SnpDto

object MutationConverter {
    fun convert(mutation: HumanMutation): MutationDto {
        return MutationDto(
            mutation.id,
            mutation.human,
            convertSnp(mutation.snp),
            mutation.mutation,
            mutation.type
        )
    }

    fun convert(from: MutationDto, snpId: Long): HumanMutationToSave {
        return HumanMutationToSave(from.human!!, snpId, from.type!!, from.mutation!!)
    }

    fun convertSnp(from: SnpDto, gene: Long): SnpToSave {
        return SnpToSave(
            gene,
            from.position!!,
            from.referenceNucleotide!!
        )
    }

    fun convertGene(from: GeneDto): Gene {
        return Gene(from.chromosome!!, from.gene!!)
    }

    fun convertSnp(from: Snp): SnpDto {
        return SnpDto(
            from.id,
            convertGene(from.gene),
            from.position,
            from.referenceNucleotide,
        )
    }

    fun convertGene(from: Gene): GeneDto {
        return GeneDto(from.id, from.chromosome, from.geneName)
    }
}
