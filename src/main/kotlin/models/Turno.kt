package models

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class Turno(
    val id: Int,
    val uuidTurno:UUID,
    val fechaInicio: LocalDateTime,
    val fechaFin:LocalDateTime
) {
}