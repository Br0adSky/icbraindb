package ru.cytogen.icbraindb.model.dto.mutation

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.domain.Sort
import ru.cytogen.icbraindb.dto.common.SortColumn
import ru.cytogen.icbraindb.model.db.mutation.Gene_
import ru.cytogen.icbraindb.model.db.mutation.HumanMutation_
import ru.cytogen.icbraindb.model.db.mutation.Snp_

enum class MutationSortColumn(private val sort: Sort.Order) : SortColumn {
    @JsonProperty("humanId")
    HUMAN(Sort.Order.by(HumanMutation_.HUMAN)),

    @JsonProperty("type")
    TYPE(Sort.Order.by(HumanMutation_.TYPE)),

    @JsonProperty("ref_nucl")
    REF_NUCL(Sort.Order.by("${HumanMutation_.SNP}.${Snp_.REFERENCE_NUCLEOTIDE}")),

    @JsonProperty("position")
    POSITION(Sort.Order.by("${HumanMutation_.SNP}.${Snp_.POSITION}")),

    @JsonProperty("mutation")
    MUTATION(Sort.Order.by(HumanMutation_.MUTATION)),

    @JsonProperty("geneName")
    GENE(Sort.Order.by("${HumanMutation_.SNP}.${Snp_.GENE}.${Gene_.GENE_NAME}")),

    @JsonProperty("geneChromosome")
    CHROMOSOME(Sort.Order.by("${HumanMutation_.SNP}.${Snp_.GENE}.${Gene_.CHROMOSOME}"));

    override fun getOrder(): Sort.Order {
        return sort
    }
}
