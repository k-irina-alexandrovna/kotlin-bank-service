package ru.kotlin.bankservice.service.impl

import ru.kotlin.bankservice.model.entity.Account
import org.springframework.stereotype.Service
import ru.kotlin.bankservice.exception.Error
import ru.kotlin.bankservice.exception.ValidationException
import ru.kotlin.bankservice.model.dto.TransferDTO
import ru.kotlin.bankservice.model.enums.BankOperation
import ru.kotlin.bankservice.service.AccountService
import ru.kotlin.bankservice.service.OperationService
import ru.kotlin.bankservice.service.TransferService
import ru.kotlin.bankservice.service.validators.Validator
import java.math.BigDecimal

@Service
class TransferServiceImpl(
    private val accountService: AccountService,
    private val operationService: OperationService,
    private val validator: Validator
): TransferService {

    override fun transfer(transferDTO: TransferDTO) {
        validator.validate(transferDTO)
        with(transferDTO){
            val senderAccount = accountService.findByNumber(senderAccountNumber!!)
            val receiverAccount = accountService.findByNumber(receiverAccountNumber!!)
            checkAccountsBeforeTransfer(senderAccount, receiverAccount)

            when(BankOperation.valueOf(operation!!)) {
                BankOperation.DEPOSIT -> makeTransfer(senderAccount, receiverAccount, amount!!)
                BankOperation.WITHDRAWAL -> makeTransfer(receiverAccount, senderAccount, amount!!)
            }
        }
    }

    private fun makeTransfer(senderAccount: Account, receiverAccount: Account, amount: BigDecimal){
        checkAccountBalance(senderAccount, amount)
        senderAccount.copy(balance = senderAccount.balance.minus(amount))
            .let { accountService.update(it) }
        receiverAccount.copy(balance = receiverAccount.balance.plus(amount))
            .let { accountService.update(it) }
    }

    private fun checkAccountBalance(account: Account, amount: BigDecimal): Error? {
        if(account.balance < amount) {
            throw ValidationException(Error.BALANCE_IS_INSUFFICIENT, "Счёт: ${account.number}, текущий баланс: ${account.balance}")
        }
        return null
    }

    private fun checkAccountsBeforeTransfer(account1: Account, account2: Account): Error? {
        if (account1.currency != account2.currency) {
            throw ValidationException(Error.CURRENCY_NOT_EQUALS_FOR_TRANSFER)
        }
        return null
    }
}