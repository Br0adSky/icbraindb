package ru.cytogen.icbraindb.exception

import ru.cytogen.icbraindb.model.dto.eeg.EEGDto

class EEGNotFound : NotFoundException {
    constructor(eeg: EEGDto) : super("EEG file $eeg not found")

    constructor(id: Int) : super("EEG file by id $id not found")
}