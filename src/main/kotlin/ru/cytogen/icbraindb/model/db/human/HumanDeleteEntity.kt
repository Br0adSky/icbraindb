package ru.cytogen.icbraindb.model.db.human

import jakarta.persistence.*

@Entity
@Table(name = "human")
class HumanDeleteEntity(
    @Id
    @Column(name = "human", nullable = false, unique = true)
    val id: String,
) {
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "human_nationalities", joinColumns = [JoinColumn(name = "human_id")])
    @Column(name = "nationality_id")
    val nationalities: Set<Long> = setOf()
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "human_diseases", joinColumns = [JoinColumn(name = "human_id")])
    @Column(name = "disease_id")
    val diseases: Set<Long> = setOf()
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "human_mutations", joinColumns = [JoinColumn(name = "human")])
    @Column(name = "id")
    val mutations: Set<Int> = setOf()
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "testsummary", joinColumns = [JoinColumn(name = "human")])
    @Column(name = "id")
    val questionnaires: Set<Int> = setOf()
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "eeg_file", joinColumns = [JoinColumn(name = "human")])
    @Column(name = "id")
    val eegFiles: Set<Int> = setOf()
}
