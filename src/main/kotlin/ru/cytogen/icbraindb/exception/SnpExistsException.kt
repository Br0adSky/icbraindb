package ru.cytogen.icbraindb.exception

import ru.cytogen.icbraindb.model.dto.mutation.SnpToEdit

class SnpExistsException(snp: SnpToEdit) :
    AlreadyExistsException("Snp: $snp already exists") {
}