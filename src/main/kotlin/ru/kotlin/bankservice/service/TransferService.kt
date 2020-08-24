package ru.kotlin.bankservice.service

import ru.kotlin.bankservice.model.Account
import java.math.BigDecimal

interface TransferService {

    fun pay(id: Long, value: BigDecimal): Account

}