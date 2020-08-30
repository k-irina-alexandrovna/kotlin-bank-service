package ru.kotlin.bankservice.model.entity

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long = 0,

        @Column(name = "full_name", nullable = false)
        val fullName: String = "",

        @JsonProperty("passport")
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
        override fun toString()=
                "user: ${fullName}, passport: $passport, accounts: ${accounts.map { it.number }}"
}