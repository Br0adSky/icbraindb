package ru.cytogen.icbraindb.service

import jakarta.persistence.EntityManager
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ru.cytogen.icbraindb.model.Human
import ru.cytogen.icbraindb.model.Mutation
import ru.cytogen.icbraindb.model.Mutation_


@Service
class MutationService(
    private val entityManager: EntityManager
) {
  fun findCommonMutations(humans: List<Human>): Iterable<Mutation> {
    val page = PageRequest.of(0, 10)
    val builder = entityManager.criteriaBuilder
    val countQuery = builder.createQuery(Long::class.java)
    val mutationsQuery = builder.createQuery(Mutation::class.java)
    val mutationRoot = mutationsQuery.from(Mutation::class.java)

    val query = countQuery.subquery(Long::class.java)
    val root = query.from(Mutation::class.java)

    val summaryJoin = root.join(Mutation_.humans)
    val inJoin = builder.`in`(summaryJoin)
    humans.forEach(inJoin::value)

    query.select(root.get(Mutation_.id))
        .where(inJoin)
        .groupBy(root.get(Mutation_.id))
        .having(builder.equal(builder.count(root), humans.size))

    countQuery.select(builder.count(query))
    mutationsQuery.where(builder.`in`(mutationRoot.get(Mutation_.id)).value(query))

    val total = entityManager.createQuery(countQuery).singleResult
    val result = entityManager.createQuery(mutationsQuery)
        .setMaxResults(page.pageSize)
        .resultList


    return PageImpl(result, page, total)
  }
}
