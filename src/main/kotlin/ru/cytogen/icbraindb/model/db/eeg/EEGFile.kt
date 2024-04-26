package ru.cytogen.icbraindb.model.db.eeg

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import ru.cytogen.icbraindb.model.db.AbstractIntEntity

@Entity
@Table(name = "eeg_file")
class EEGFile(
    @Column(name = "human")
    val human: String?,
    @Column(name = "eeg_id", nullable = false)
    var eegId: String,
    @Column(name = "description")
    var description: String?,
    @Column(name = "path", nullable = false)
    var path: String,
    @Column(name = "filename", nullable = false)
    var filename: String
) : AbstractIntEntity()
