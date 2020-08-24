package ru.kotlin.bankservice.service.impl

import ru.kotlin.bankservice.model.entity.Account
import org.springframework.stereotype.Service
import ru.kotlin.bankservice.exception.EntityNotFoundException
import ru.kotlin.bankservice.exception.TransferException
import ru.kotlin.bankservice.exception.ValidationException
import ru.kotlin.bankservice.model.dto.TransferDTO
import ru.kotlin.bankservice.model.enums.BankOperation
import ru.kotlin.bankservice.service.AccountService
import ru.kotlin.bankservice.service.OperationService
import ru.kotlin.bankservice.service.TransferService
import java.math.BigDecimal

@Service
class TransferServiceImpl(
    private val accountService: AccountService,
    private val operationService: OperationService
): TransferService {

    override fun execute(transferDTO: TransferDTO) {
        validate(transferDTO)
        with(transferDTO){
            val senderAccount = accountService.getByNumber(senderAccountNumber)
            val receiverAccount = accountService.getByNumber(receiverAccountNumber)

            checkAccountCurrencies(senderAccount, receiverAccount)
            when(BankOperation.valueOf(operation)) {
                BankOperation.DEPOSIT -> makeTransfer(senderAccount, receiverAccount, amount)
                BankOperation.WITHDRAWAL -> makeTransfer(receiverAccount, senderAccount, amount)
            }
        }
    }

    private fun makeTransfer(fromAccount: Account, toAccount: Account, amount: BigDecimal){
        checkAccountBalance(fromAccount, amount)
        fromAccount.copy(balance = fromAccount.balance.minus(amount)).let { accountService.update(it) }
        toAccount.copy(balance = toAccount.balance.plus(amount)).let { accountService.update(it) }
    }

    private fun checkAccountCurrencies(account1: Account, account2: Account) =
        (account1.currency == account2.currency).let { isEquals ->
            if(!isEquals) {
                throw ValidationException("Failed to validate transfer: ${account1.currency} != ${account2.currency}")
            }
        }

    private fun checkAccountBalance(account: Account, amount: BigDecimal) {
        if(account.balance < amount) {
            throw TransferException(
                "Account balance ${account.number} am insufficient. ${account.balance} < $amount. Operation rejected."
            )
        }
    }

    fun validate(transferDTO: TransferDTO) =
        with(transferDTO) {
            (checkExistAccount(senderAccountNumber)
                    && checkExistAccount(receiverAccountNumber)
                    && checkBankOperationExists(operation)
                    && amount > BigDecimal.ZERO
                    ).let { isValid ->
                    if (!isValid) {
                        throw ValidationException("Failed to validate transfer $transferDTO")
                    }
                }
        }

    private fun checkExistAccount(number: Number) =
        accountService.isExists(number.toLong()).let { isExists ->
            if (!isExists) {
                true
            } else {
                throw EntityNotFoundException("account $number not found")
            }
        }

    private fun checkBankOperationExists(operation: String) = try {
        BankOperation.valueOf(operation)
        true
    } catch (e: Exception) {
        false
    }
}