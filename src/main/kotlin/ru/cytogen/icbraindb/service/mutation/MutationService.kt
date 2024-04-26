package ru.cytogen.icbraindb.service.mutation

import mu.KLogging
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional
import ru.cytogen.icbraindb.dto.request.MutationRequest
import ru.cytogen.icbraindb.dto.response.Response
import ru.cytogen.icbraindb.exception.MutationNotFound
import ru.cytogen.icbraindb.filter.MutationFilter
import ru.cytogen.icbraindb.model.db.mutation.HumanMutation
import ru.cytogen.icbraindb.model.dto.mutation.MutationDto
import ru.cytogen.icbraindb.model.dto.mutation.MutationToEdit
import ru.cytogen.icbraindb.repository.MutationRepository
import ru.cytogen.icbraindb.repository.MutationToSaveRepository
import ru.cytogen.icbraindb.service.human.HumanService
import ru.cytogen.icbraindb.service.utils.PageConverter

@Service
class MutationService(
    private val repo: MutationRepository,
    private val humanService: HumanService,
    private val repoToSave: MutationToSaveRepository,
    private val snpService: SnpService
) {
    companion object : KLogging()
    @Transactional
    fun getById(id: Int): MutationDto {
        return repo.findById(id).map { MutationConverter.convert(it) }.orElseThrow { MutationNotFound(id) }
    }

    @Transactional
    fun getAll(request: MutationRequest): Response {
        val pagination = PageConverter.of(request)
        val data = request.filter?.let {
            repo.findAll(getSpecification(it), pagination)
        } ?: repo.findAll(pagination)
        return Response.from(request, data, MutationConverter::convert)
    }

    private fun getSpecification(filter: MutationFilter): Specification<HumanMutation> {
        return Specification.where(filter.mutation?.let(MutationSpecification::mutation))
            .and(filter.types?.let(MutationSpecification::type))
            .and(filter.chromosome?.let(MutationSpecification::chromosome))
            .and(filter.gene?.let(MutationSpecification::gene))
            .and(filter.position?.let(MutationSpecification::position))
            .and(filter.humans?.takeIf { it.value.isNotEmpty() }?.let(MutationSpecification::humans))
            .and(filter.refNucl?.let(MutationSpecification::refNucl))
    }

    @Transactional
    fun saveEdited(request: MutationToEdit) {
        repo.findById(request.id!!)
            .map {
                it.mutation = request.mutation!!
                it.type = request.type!!
                logger.info { "Мутация с id: ${request.id} обновлена: $request" }
            }
            .orElseThrow { MutationNotFound(request.id) }
    }

    //необходимо выполнять запросы последовательно, так как в рамках транзакции могут
    //добавиться новые записи, которые необходимо учитывать в сл. вызове
    @Transactional(isolation = Isolation.SERIALIZABLE)
    fun saveNew(request: MutationDto) {
        humanService.verifyHumanExists(request.human!!)
        val snp = snpService.findSnpIdOrCreate(request.snp!!)

        repoToSave.persist(MutationConverter.convert(request, snp))
        logger.info { "Сохранена новая мутация: $request" }
    }

    @Transactional
    fun delete(id: Int) {
        repo.deleteById(id)
        logger.info { "Удалена мутация с id: $id" }
    }
}
