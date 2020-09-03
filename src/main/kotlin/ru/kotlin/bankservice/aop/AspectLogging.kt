package ru.kotlin.bankservice.aop

import mu.KotlinLogging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.context.annotation.Configuration
import java.lang.System.currentTimeMillis

@Configuration
@Aspect
class AspectLogging {

    @Around("within(org.springframework.data.repository.Repository+) && execution(* *(..))")
    fun aroundSpringRepositories(joinPoint: ProceedingJoinPoint): Any? = joinPoint
        .run {
            val startTime = currentTimeMillis()
            joinPoint.proceed()
                .also {
                    LOG.info("${joinPoint.signature.toShortString()}: ${currentTimeMillis() - startTime} ms, returned: $it")
                }
        }

    @AfterThrowing(pointcut = "execution(* ru.kotlin.bankservice.service..*.*(..))", throwing = "exception")
    fun afterThrowing(joinPoint: JoinPoint, exception: Exception) {
        LOG.error(
            "Exception in {}.{}(). Message: {}. Cause = {}",
            joinPoint.signature.declaringTypeName,
            joinPoint.signature.name,
            exception.message?.let { it } ?: "NULL",
            exception.cause?.let { it } ?: "NULL"
        )
    }

    companion object {
        private val LOG = KotlinLogging.logger { }
    }
}
