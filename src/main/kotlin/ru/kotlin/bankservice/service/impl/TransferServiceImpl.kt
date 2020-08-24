package ru.kotlin.bankservice.service.impl

import ru.kotlin.bankservice.model.Account
import ru.kotlin.bankservice.repository.AccountRepository
import org.springframework.stereotype.Service
import ru.kotlin.bankservice.service.AccountService
import ru.kotlin.bankservice.service.TransferService
import java.math.BigDecimal
import javax.management.BadAttributeValueExpException

@Service
class TransferServiceImpl(
    private val accountService: AccountService
): TransferService {

    override fun pay(id: Long, value: BigDecimal): Account {
        accountService.get(id).let { account ->
            if (account.balance > value) {
                account.balance.minus(value)
                return accountService.update(id, account)
            } else {
                throw BadAttributeValueExpException("Balance less then value")
            }
        }
    }

}