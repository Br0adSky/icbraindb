package ru.cytogen.icbraindb.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.cytogen.icbraindb.dto.HumanDto
import ru.cytogen.icbraindb.repository.HumanRepository

@RestController
@RequestMapping("api/human")
class HumanController(
    private val repo: HumanRepository
) {
    @GetMapping("/")
    fun getAllHumans(): List<HumanDto> {
        return repo.findAll().map {
            HumanDto(
                it.id,
                it.age,
                it.comments,
                it.ethos,
                it.nationality,
                it.nationalityEn,
                it.city,
                it.country,
                it.district,
                it.sex
            )
        }
    }
}