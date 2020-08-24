package ru.kotlin.bankservice.model

import javax.persistence.*

@Entity
@Table(name = "operations")
data class Operation(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    val account: Account,

    @Column(name = "description")
    val description: String?

): AbstractBaseEntity<Long>()