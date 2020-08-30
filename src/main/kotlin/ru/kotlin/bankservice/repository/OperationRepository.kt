package ru.kotlin.bankservice.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.kotlin.bankservice.model.entity.Operation

@Repository
interface OperationRepository : JpaRepository<Operation, Long>