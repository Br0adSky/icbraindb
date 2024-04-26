package ru.cytogen.icbraindb.exception

import ru.cytogen.icbraindb.model.dto.mutation.SnpToEdit

class SnpNotFound : NotFoundException {
    constructor(id: Long) : super("Snp: $id not found")
    constructor(snp: SnpToEdit) : super("Snp: $snp not found")
}