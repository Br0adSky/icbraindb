package ru.cytogen.icbraindb.model.db.mutation

import jakarta.persistence.*
import ru.cytogen.icbraindb.model.db.AbstractLongEntity

@Entity
@Table(name = "genes")
class Gene(
    @Column(name = "chromosome", nullable = false)
    var chromosome: String,
    @Column(name = "gene_name", nullable = false)
    var geneName: String
) : AbstractLongEntity() {
    //компромисс, почему-то если делать как везде через @ElementCollection, то возникает какая-то ошибка в human-mutations
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE], mappedBy = "gene")
    val snps: MutableSet<SnpToDelete> = mutableSetOf()
}
