package ru.kotlin.bankservice.controller

import javassist.NotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(value = [(NotFoundException::class)])
    fun notFound(exception: NotFoundException) = exception.localizedMessage
}