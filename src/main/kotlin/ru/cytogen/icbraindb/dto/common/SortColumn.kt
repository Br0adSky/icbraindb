package ru.cytogen.icbraindb.dto.common

import org.springframework.data.domain.Sort

interface SortColumn {
    fun getOrder(): Sort.Order
}
