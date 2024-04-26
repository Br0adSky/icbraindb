package ru.cytogen.icbraindb.controller

import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.cytogen.icbraindb.dto.response.ErrorResponse
import ru.cytogen.icbraindb.exception.AlreadyExistsException
import ru.cytogen.icbraindb.exception.NotFoundException

@ControllerAdvice
class ExceptionController {
    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorResponse> {
        logger.error(ex) { "Получена ошибка" }
        return ResponseEntity.internalServerError()
            .body(ErrorResponse("Internal exception"))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        logger.error(ex) { "Ошибка валидации запроса" }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse(
                ex.fieldErrors.joinToString(
                    prefix = "Validation exception on ",
                    separator = ", "
                ) {
                    "field \"${it.field}\": ${it.defaultMessage}"
                })
        )
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotExistsException(ex: NotFoundException): ResponseEntity<ErrorResponse> {
        logger.error(ex) { "Не найдена запись по запросу" }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(ex.message))
    }

    @ExceptionHandler(AlreadyExistsException::class)
    fun handleAlreadyExistsException(ex: AlreadyExistsException): ResponseEntity<ErrorResponse> {
        logger.error(ex) { "Невозможно создать запись" }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(ex.message))
    }

    companion object : KLogging()
}