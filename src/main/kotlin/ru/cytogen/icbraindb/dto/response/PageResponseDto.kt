package ru.cytogen.icbraindb.dto.response

class PageResponseDto(
    val number: Int,
    val size: Int,
    val totalPages: Int,
    val totalElements: Long
) {
}
