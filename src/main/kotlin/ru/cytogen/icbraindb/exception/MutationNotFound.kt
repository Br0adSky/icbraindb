package ru.cytogen.icbraindb.exception

class MutationNotFound : NotFoundException {
    constructor(id: Int) : super("Mutation id: $id not found")
}