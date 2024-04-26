package ru.cytogen.icbraindb.exception

class HumanNotExistsException(humanID: String) : NotFoundException("Human with id: $humanID not found") {
}