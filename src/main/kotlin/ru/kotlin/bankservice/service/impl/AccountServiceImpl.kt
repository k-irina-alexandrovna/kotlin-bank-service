package ru.kotlin.bankservice.service.impl

import ru.kotlin.bankservice.model.entity.Account
import ru.kotlin.bankservice.repository.AccountRepository
import ru.kotlin.bankservice.service.AccountService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.kotlin.bankservice.exception.EntityNotFoundException
import ru.kotlin.bankservice.exception.ValidationException
import ru.kotlin.bankservice.model.dto.AccountDTO
import ru.kotlin.bankservice.model.enums.Currency
import ru.kotlin.bankservice.service.UserService
import java.math.BigDecimal

@Service
class AccountServiceImpl(
    private val accountRepository: AccountRepository,
    private val userService: UserService
): AccountService {

    @Transactional(readOnly = true)
    override fun getAll(): Iterable<Account> = accountRepository.findAll()

    @Transactional(readOnly = true)
    override fun get(id: Long) = accountRepository.getOne(id)

    override fun create(accountDTO: AccountDTO): Account {
        validate(accountDTO)
        return Account(
            number = accountDTO.number,
            balance = accountDTO.balance,
            currency = Currency.valueOf(accountDTO.currency),
            user = userService.get(accountDTO.userId)
        ).let { accountRepository.save(it) }
    }

    override fun update(id: Long, account: Account) =
        if (isExists(id)) {
            accountRepository.save(account)
        } else {
            throw EntityNotFoundException("Account $id not found")
        }

    override fun update(account: Account): Account = accountRepository.save(account)

    override fun delete(id: Long) = accountRepository.deleteById(id)

    @Transactional(readOnly = true)
    override fun isExists(id: Long) = accountRepository.existsById(id)

    @Transactional(readOnly = true)
    override fun existsByNumber(number: Number): Boolean = accountRepository.existsByNumber(number)

    @Transactional(readOnly = true)
    override fun getByNumber(number: Number): Account = accountRepository.getByNumber(number)

    private fun validate(accountDTO: AccountDTO) = (
            accountDTO.number.toString().isNotBlank()
                    && currencyExists(accountDTO.currency)
                    && userService.isExists(accountDTO.userId)
            ).let { isValid ->
            if (!isValid) {
                throw ValidationException("Failed to validate account $accountDTO")
            }
        }

    private fun currencyExists(currency: String) = try {
        Currency.valueOf(currency)
        true
    } catch (e: Exception) {
        false
    }
}