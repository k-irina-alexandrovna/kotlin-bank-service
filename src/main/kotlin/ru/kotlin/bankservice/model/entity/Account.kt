package ru.kotlin.bankservice.model.entity

import ru.kotlin.bankservice.model.enums.Currency
import java.math.BigDecimal
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "accounts")
data class Account(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long = 0L,

    @Column(
        name = "number",
        unique = true,
        nullable = false
    )
    val number: Number,

    @Column(name = "balance")
    val balance: BigDecimal = BigDecimal.ZERO,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "currency")
    val currency: Currency,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id")
    val user: User,

    @OneToMany(
        mappedBy = "account",
        cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    val operations: MutableList<Operation>? = mutableListOf()

) : AbstractBaseEntity<Long>() {
    override fun toString() = "account: ${this.number}, user: ${user.fullName}"
}
