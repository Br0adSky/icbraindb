package ru.cytogen.icbraindb.model.db.questionnaire

import jakarta.persistence.*
import ru.cytogen.icbraindb.model.db.AbstractIntEntity

@Entity
@Table(name = "testsummary2test")
class Summary(
    @ManyToOne
    @JoinColumn(name = "test", nullable = false)
    val test: Test,
    @Column(name = "testsummaryalias", nullable = false)
    var alias: String,
    @Column(name = "description")
    var description: String?,
    @Column(name = "description_en")
    var descriptionEN: String?
) : AbstractIntEntity() {

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "testsummary", joinColumns = [JoinColumn(name = "summary_id")])
    @Column(name = "id")
    val questionnaires: Set<Int> = setOf()
}
