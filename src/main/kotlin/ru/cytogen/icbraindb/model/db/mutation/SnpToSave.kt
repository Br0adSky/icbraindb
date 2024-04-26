package ru.cytogen.icbraindb.model.db.mutation

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import ru.cytogen.icbraindb.model.db.AbstractLongEntity

@Entity
@Table(name = "mutation_snp")
class SnpToSave(
    @Column(name = "gene_id", nullable = false)
    val gene: Long,
    @Column(name = "position", nullable = false)
    var position: Long,
    @Column(name = "ref_nucl", nullable = false)
    var referenceNucleotide: String,
) : AbstractLongEntity() {

}
