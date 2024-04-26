package ru.cytogen.icbraindb.dto.request

import ru.cytogen.icbraindb.filter.EEGFilter
import ru.cytogen.icbraindb.filter.HumanFilter
import ru.cytogen.icbraindb.filter.MutationFilter
import ru.cytogen.icbraindb.filter.QuestionnaireFilter
import ru.cytogen.icbraindb.filter.service.FilterParser
import ru.cytogen.icbraindb.filter.service.SortParser
import ru.cytogen.icbraindb.model.dto.eeg.EEGSortColumn
import ru.cytogen.icbraindb.model.dto.human.HumanSortColumn
import ru.cytogen.icbraindb.model.dto.mutation.MutationSortColumn
import ru.cytogen.icbraindb.model.dto.questionnaire.QuestionnaireSortColumn

typealias HumanRequest = Request<HumanFilter, HumanSortColumn>
typealias MutationRequest = Request<MutationFilter, MutationSortColumn>
typealias QuestionnaireRequest = Request<QuestionnaireFilter, QuestionnaireSortColumn>
typealias EEGRequest = Request<EEGFilter, EEGSortColumn>

val parsedHumanFilter = FilterParser.getFilterCache(HumanFilter::class)
val parsedMutationFilter = FilterParser.getFilterCache(MutationFilter::class)
val parsedQuestionnaireFilter = FilterParser.getFilterCache(QuestionnaireFilter::class)
val parsedEEGFilter = FilterParser.getFilterCache(EEGFilter::class)

val parsedHumanSort = SortParser.getSortScheme(HumanSortColumn::class)
val parsedMutationSort = SortParser.getSortScheme(MutationSortColumn::class)
val parsedQuestionnaireSort = SortParser.getSortScheme(QuestionnaireSortColumn::class)
val parsedEEGSort = SortParser.getSortScheme(EEGSortColumn::class)