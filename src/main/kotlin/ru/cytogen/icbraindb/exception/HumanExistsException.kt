package ru.cytogen.icbraindb.exception

class HumanExistsException(humanID: String) : AlreadyExistsException("Human with id: $humanID already exists") {
}