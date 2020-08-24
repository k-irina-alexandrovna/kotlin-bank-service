package ru.kotlin.bankservice.controller

import ru.kotlin.bankservice.model.Account
import ru.kotlin.bankservice.service.TransferService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import ru.kotlin.bankservice.model.Operation
import ru.kotlin.bankservice.service.OperationService
import java.math.BigDecimal
import javax.validation.constraints.NotNull

@RestController("/payment")
class PaymentController(
    private val operationService: OperationService,
    private val transferService : TransferService
) {

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun pay(
        @NotNull @RequestParam id: Long,
        @NotNull @RequestParam value: BigDecimal,
        @RequestParam description: String
    ): Account {
        transferService.pay(id, value).let { account ->
//            operationService.save(
//                Operation(0L, account, description)
//            )
            return account
        }
    }

}