package ru.cytogen.icbraindb.exception

import ru.cytogen.icbraindb.model.dto.questionnaire.SummaryDto

class SummaryNotFound(summary: SummaryDto) :
    NotFoundException("Summary: $summary not found") {
}