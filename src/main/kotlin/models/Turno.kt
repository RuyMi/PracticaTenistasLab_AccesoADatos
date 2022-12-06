package models

import kotlinx.serialization.Serializable
import serializers.LocalDateSerializer
import serializers.LocalDateTimeSerializer
import serializers.UUIDSerializer
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
@Serializable
data class Turno(
    val id: Int,
    @Serializable(UUIDSerializer::class)
    val uuidTurno:UUID,
    @Serializable(LocalDateTimeSerializer::class)
    val fechaInicio: LocalDateTime,
    @Serializable(LocalDateTimeSerializer::class)
    val fechaFin:LocalDateTime
) {
}