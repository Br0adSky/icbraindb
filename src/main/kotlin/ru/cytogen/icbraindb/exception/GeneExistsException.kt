package ru.cytogen.icbraindb.exception

import ru.cytogen.icbraindb.model.dto.mutation.GeneDto

class GeneExistsException :
    AlreadyExistsException {
    constructor(gene: GeneDto) : super("Gene: $gene already exists")
}