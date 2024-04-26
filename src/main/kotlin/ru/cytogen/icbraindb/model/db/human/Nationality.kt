package ru.cytogen.icbraindb.model.db.human

import jakarta.persistence.*
import ru.cytogen.icbraindb.model.db.AbstractLongEntity

@Entity
@Table(name = "nationalities")
class Nationality(
    @Column(name = "nationality")
    var nationality: String?,
    @Column(name = "nationality_en")
    var nationalityEN: String?
) : AbstractLongEntity() {
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "human_nationalities", joinColumns = [JoinColumn(name = "nationality_id")])
    @Column(name = "human_id")
    val humans: MutableSet<String> = mutableSetOf()
}
