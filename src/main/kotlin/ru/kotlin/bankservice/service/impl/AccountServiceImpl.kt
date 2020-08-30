package ru.kotlin.bankservice.service.impl

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service
import ru.kotlin.bankservice.exception.ObjectAlreadyExists
import ru.kotlin.bankservice.exception.ObjectNotFoundException
import ru.kotlin.bankservice.model.dto.AccountRequestDTO
import ru.kotlin.bankservice.model.entity.Account
import ru.kotlin.bankservice.model.enums.Currency
import ru.kotlin.bankservice.repository.AccountRepository
import ru.kotlin.bankservice.service.AccountService
import ru.kotlin.bankservice.service.UserService
import ru.kotlin.bankservice.service.validators.Validator

@Service
class AccountServiceImpl(
    private val accountRepository: AccountRepository,
    private val userService: UserService,
    private val validator: Validator
): AccountService {

    override fun getAll(): Iterable<Account> = accountRepository.findAll()

    override fun get(id: Long) = accountRepository.findById(id)
        .let {
            if(it.isPresent) {
                it.get()
            } else {
                throw ObjectNotFoundException("Счёт с id $id не найден")
            }
        }

    override fun create(accountRequest: AccountRequestDTO): Account {
        validator.validate(accountRequest)
        if(isExistsByNumber(accountRequest.number!!)){
            throw ObjectAlreadyExists("Счёт с номером ${accountRequest.number} уже зарегистрирован")
        }
        return Account(
            number = accountRequest.number,
            balance = accountRequest.balance,
            currency = Currency.valueOf(accountRequest.currency!!),
            user = userService.get(accountRequest.userId!!)
        ).let { accountRepository.save(it) }
    }

    override fun update(id: Long, accountRequest: AccountRequestDTO) = get(id)
        .copy( balance = accountRequest.balance)
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