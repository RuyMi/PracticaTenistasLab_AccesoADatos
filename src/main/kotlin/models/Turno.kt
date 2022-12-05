package models

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

/**
 * Turno
 *
 * @property id
 * @property uuidTurno
 * @property fechaInicio
 * @property fechaFin
 * @constructor Create empty Turno
 */
data class Turno(
    val id: Int,
    val uuidTurno:UUID,
    val fechaInicio: LocalDateTime,
    val fechaFin:LocalDateTime
) {
}