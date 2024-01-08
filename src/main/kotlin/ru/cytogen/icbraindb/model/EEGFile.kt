package ru.cytogen.icbraindb.model

import jakarta.persistence.*

@Entity
@Table(name = "eeg_file")
class EEGFile(
    @ManyToOne
    @JoinColumn(name = "human")
    val human: Human?,
    @Column(name = "eeg_id")
    val eegId: String?,
    @Column(name = "description")
    val description: String?,
    @Column(name = "path", nullable = false)
    val path: String?,
    @Column(name = "filename", nullable = false)
    val filename: String?
) : AbstractIntEntity()
