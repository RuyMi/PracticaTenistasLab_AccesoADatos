package models

import kotlinx.serialization.Serializable
import org.hibernate.annotations.Type
import serializers.LocalDateTimeSerializer
import serializers.UUIDSerializer
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

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
@Entity
@Table(name = "Turnos")
@NamedQueries(
    NamedQuery(name = "Turnos.findAll", query = "SELECT t FROM Turno t"),
    NamedQuery(
        name = "Turno.porUUID",
        query = "SELECT t FROM Turno t WHERE t.uuidTurno = :id"
    ),
)
data class Turno(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    @Serializable(UUIDSerializer::class)
    @Column(name="UUID_Turno")
    @Type(type = "uuid-char")
    val uuidTurno: UUID,
    @Serializable(LocalDateTimeSerializer::class)
    val fechaInicio: LocalDateTime,
    @Serializable(LocalDateTimeSerializer::class)
    val fechaFin: LocalDateTime
): java.io.Serializable {
}