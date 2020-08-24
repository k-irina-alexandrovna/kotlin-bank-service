package ru.kotlin.bankservice.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "accounts")
data class Account(

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long? = null,

        @Column(name = "number", unique = true) //TODO добавить обработку исключения
        val number: Number = 0,

        @Column(name = "balance")
        val balance: BigDecimal = BigDecimal.ZERO,

        @Column(name = "description")
        val description: String? = null,

//        @OneToMany(mappedBy = "account", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//        val users: List<User>? = emptyList(),

        @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        val user: User,

        @OneToMany(mappedBy = "account", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        @JoinColumn(name = "account_id")
        val operations: List<Operation> = emptyList()

): AbstractBaseEntity<Long>() {
        override fun toString()= "account: ${this.number}, user: ${user?.getFullName()}"
}