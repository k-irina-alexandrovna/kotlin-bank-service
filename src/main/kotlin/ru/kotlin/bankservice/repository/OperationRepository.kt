package ru.kotlin.bankservice.repository

import ru.kotlin.bankservice.model.entity.Operation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OperationRepository : JpaRepository<Operation, Long>