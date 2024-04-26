package ru.cytogen.icbraindb.exception

import ru.cytogen.icbraindb.model.dto.mutation.GeneDto

class GeneNotFound : NotFoundException {
    constructor(gene: GeneDto) : super("Gene: $gene not found")

    constructor(id: Long): super("Gene id: $id not found")
}