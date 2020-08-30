package ru.kotlin.bankservice.service.impl

import org.springframework.dao.EmptyResultDataAccessException
import ru.kotlin.bankservice.model.entity.Account
import ru.kotlin.bankservice.repository.AccountRepository
import ru.kotlin.bankservice.service.AccountService
import org.springframework.stereotype.Service
import ru.kotlin.bankservice.exception.ObjectAlreadyExists
import ru.kotlin.bankservice.exception.ObjectNotFoundException
import ru.kotlin.bankservice.model.dto.AccountDTO
import ru.kotlin.bankservice.model.enums.Currency
import ru.kotlin.bankservice.service.UserService
import ru.kotlin.bankservice.service.validators.Validator

@Service
class AccountServiceImpl(
    private val accountRepository: AccountRepository,
    private val userService: UserService,
    private val validator: Validator
): AccountService {

    override fun getAll(): Iterable<Account> = accountRepository.findAll()

    override fun find(id: Long) = accountRepository.findById(id)
        .let {
            if(it.isPresent) {
                it.get()
            } else {
                throw ObjectNotFoundException("Счёт с id $id не найден")
            }
        }

    override fun create(account: AccountDTO): Account {
        validator.validate(account)
        if(isExistsByNumber(account.number!!)){
            throw ObjectAlreadyExists("Счёт с номером ${account.number} уже зарегистрирован")
        }
        return Account(
            number = account.number,
            balance = account.balance,
            currency = Currency.valueOf(account.currency!!),
            user = userService.get(account.userId!!)
        ).let { accountRepository.save(it) }
    }

    override fun update(id: Long, account: AccountDTO) = find(id)
        .copy( balance = account.balance)
        .let {
            accountRepository.save(it)
        }

    override fun update(account: Account): Account = accountRepository.save(account)

    override fun delete(id: Long) = accountRepository.deleteById(id)

    override fun findByNumber(number: Number): Account =
        try {
            accountRepository.findByNumber(number)
        } catch (e: EmptyResultDataAccessException) {
            throw ObjectNotFoundException("Счёт $number не найден")
        }

    private fun isExistsByNumber(number: Number) = accountRepository.existsByNumber(number)
}