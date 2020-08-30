package ru.kotlin.bankservice.model.entity

import ru.kotlin.bankservice.model.enums.BankOperation
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "operations")
data class Operation(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long,

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    val account: Account,

    @Column(name = "amount", nullable = false)
    val amount: BigDecimal,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "operation", nullable = false)
    val operation: BankOperation

): AbstractBaseEntity<Long>() {
    override fun toString()= "${this.createdTime}, account: ${account.number}, operation: ${operation.name}, amount: $amount"
}