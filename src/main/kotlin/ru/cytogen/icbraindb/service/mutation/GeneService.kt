package ru.cytogen.icbraindb.service.mutation

import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.cytogen.icbraindb.exception.GeneExistsException
import ru.cytogen.icbraindb.exception.GeneNotFound
import ru.cytogen.icbraindb.model.dto.mutation.GeneDto
import ru.cytogen.icbraindb.repository.GeneRepository
import java.util.*

@Service
class GeneService(
    private val geneRepo: GeneRepository
) {
    companion object : KLogging()

    fun findGeneIdOrCreate(gene: GeneDto): Long {
        return findGeneOpt(gene).orElseGet { createGene(gene) }
    }

    private fun createGene(gene: GeneDto): Long {
        val result = geneRepo.save(MutationConverter.convertGene(gene)).id!!
        logger.info { "Создан новый ген: $gene с id: $result" }
        return result
    }

    private fun verifyExistsById(id: Long) {
        if (!geneRepo.existsById(id)) {
            throw GeneNotFound(id)
        }
    }

    private fun findGeneOpt(gene: GeneDto): Optional<Long> {
        gene.id?.let {
            verifyExistsById(it)
            return Optional.of(it)
        }

        return geneRepo.findByChromosomeAndGeneName(gene.chromosome!!, gene.gene!!).map { it.getId() }
    }

    @Transactional
    fun saveEdited(request: GeneDto) {
        geneRepo.findById(request.id!!).map {
            if (geneRepo.existsByChromosomeAndGeneName(request.chromosome!!, request.gene!!)) {
                throw GeneExistsException(request)
            }
            it.geneName = request.gene
            it.chromosome = request.chromosome
            logger.info { "Ген с id: ${request.id} обновлен: $request" }
        }.orElseThrow { GeneNotFound(request) }
    }

    @Transactional
    fun getById(id: Long): GeneDto {
        return geneRepo.findById(id).map { MutationConverter.convertGene(it) }.orElseThrow { GeneNotFound(id) }
    }

    @Transactional
    fun delete(id: Long) {
        geneRepo.deleteById(id)
        logger.info { "Удален ген с id: $id" }
    }
}