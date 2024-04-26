package ru.cytogen.icbraindb.model.db.human

import jakarta.persistence.*
import ru.cytogen.icbraindb.model.db.AbstractLongEntity

@Entity
@Table(name = "diseases")
class Disease(
    @Column(name = "disease", nullable = false)
    var disease: String
) : AbstractLongEntity() {
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "human_diseases", joinColumns = [JoinColumn(name = "disease_id")])
    @Column(name = "human_id")
    val humans: MutableSet<String> = mutableSetOf()
}
