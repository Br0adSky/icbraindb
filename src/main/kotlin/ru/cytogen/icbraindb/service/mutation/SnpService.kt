package ru.cytogen.icbraindb.service.mutation

import mu.KLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.cytogen.icbraindb.exception.SnpExistsException
import ru.cytogen.icbraindb.exception.SnpNotFound
import ru.cytogen.icbraindb.model.dto.mutation.SnpDto
import ru.cytogen.icbraindb.model.dto.mutation.SnpToEdit
import ru.cytogen.icbraindb.repository.SnpRepository
import ru.cytogen.icbraindb.repository.SnpToSaveRepository
import java.util.*

@Service
class SnpService(
    private val snpRepo: SnpRepository,
    private val geneService: GeneService,
    private val toSaveRepo: SnpToSaveRepository
) {
    companion object : KLogging()

    @Transactional
    fun saveEdited(snp: SnpToEdit) {
        snpRepo.findById(snp.id!!)
            .map {
                if (snpRepo.existsByReferenceNucleotideAndPosition(snp.referenceNucleotide!!, snp.position!!)) {
                    throw SnpExistsException(snp)
                }
                it.position = snp.position
                it.referenceNucleotide = snp.referenceNucleotide
                logger.info { "SNP с id: ${snp.id} обновлен: $snp" }
            }
            .orElseThrow { SnpNotFound(snp) }
    }

    @Transactional
    fun findSnpIdOrCreate(snp: SnpDto): Long {
        return findSnpId(snp).orElseGet {
            createNew(snp)
        }
    }

    private fun createNew(snp: SnpDto): Long {
        val result = toSaveRepo.save(
            MutationConverter.convertSnp(
                snp,
                geneService.findGeneIdOrCreate(snp.gene!!)
            )
        ).id!!
        logger.info { "Создан новый SNP: $snp с id: $result" }
        return result
    }

    private fun verifyExistsById(id: Long) {
        if (!snpRepo.existsById(id)) {
            throw SnpNotFound(id)
        }
    }

    private fun findSnpId(snp: SnpDto): Optional<Long> {
        snp.id?.let {
            verifyExistsById(it)
            return Optional.of(snp.id)
        }

        return snpRepo.findByReferenceNucleotideAndPosition(
            snp.referenceNucleotide!!,
            snp.position!!
        ).map { it.getId() }
    }

    @Transactional
    fun getById(id: Long): SnpDto {
        return snpRepo.findById(id)
            .map { MutationConverter.convertSnp(it) }
            .orElseThrow { SnpNotFound(id) }
    }

    @Transactional
    fun delete(id: Long) {
        snpRepo.deleteById(id)
        logger.info { "Удален SNP с id: $id" }
    }
}