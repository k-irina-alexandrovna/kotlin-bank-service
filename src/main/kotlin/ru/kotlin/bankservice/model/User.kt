package ru.kotlin.bankservice.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        override var id: Long? = null,

        @Column(name = "lastName")
        val lastName: String = "",

        @Column(name = "firstName")
        val firstName: String = "",

        @Column(name = "middleName")
        val middleName: String = "",

        @Column(name = "birthday")
        val birthday: LocalDate?,

        @JsonProperty("passportSerialNumber")
        @Column(name = "passport", unique = true)
        val passport: String,

//        @JsonIgnore
//        @ManyToOne(fetch = FetchType.LAZY)
//        @JoinColumn(name = "account_id")
//        var account: Account? = null

        @JsonIgnore
        @OneToMany(mappedBy = "account", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        @JoinColumn(name = "account_id")
        var accounts: List<Account> = emptyList()

): AbstractBaseEntity<Long>() {
        fun getFullName() = "$lastName $firstName $middleName"
        override fun toString()= "user: ${getFullName()}, passport: $passport, accounts: ${accounts.map { it.number }}"
}