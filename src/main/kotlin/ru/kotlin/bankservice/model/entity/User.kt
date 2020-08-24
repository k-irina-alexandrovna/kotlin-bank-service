package ru.kotlin.bankservice.model.entity

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long = 0,

        @Column(name = "lastName", nullable = false)
        val lastName: String = "",

        @Column(name = "firstName", nullable = false)
        val firstName: String = "",

        @Column(name = "middleName")
        val middleName: String = "",

        @Column(name = "birth_date", nullable = false)
        val birthDate: LocalDate,

        @JsonProperty("passportSerialNumber")
        @Column(name = "passport", unique = true, nullable = false)
        val passport: String,

        @OneToMany(
                mappedBy = "user",
                cascade = [CascadeType.ALL],
                fetch = FetchType.LAZY,
                orphanRemoval = true
        )
        val accounts: MutableList<Account> = mutableListOf()

): AbstractBaseEntity<Long>() {
        fun getFullName() = "$lastName $firstName $middleName"
        override fun toString()= "user: ${getFullName()}, passport: $passport, accounts: ${accounts.map { it.number }}"
}