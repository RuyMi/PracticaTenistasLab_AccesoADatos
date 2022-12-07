package models

import kotlinx.serialization.Serializable
import models.enums.TipoEstado
import org.hibernate.annotations.Type
import serializers.LocalDateSerializer
import serializers.UUIDSerializer
import java.time.LocalDate
import java.util.*
import javax.persistence.*

/**
 * Pedidos
 *
 * @property id
 * @property uuid
 * @property estado
 * @property fechaEntrada
 * @property fechaSalidaProgramada
 * @property fechaEntrega
 * @property precio
 * @constructor Create empty Pedidos
 */
@Serializable
@Entity
@Table(name = "Pedidos")
@NamedQueries(
    NamedQuery(name = "Pedidos.findAll", query = "SELECT t FROM Pedidos t"),
    NamedQuery(
        name = "Pedidos.porUUID",
        query = "SELECT t FROM Pedidos t WHERE t.uuid = t"
    ),
)
data class Pedidos(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int,
    @Serializable(UUIDSerializer::class)
    @Column(name="UUID_Pedidos")
    @Type(type = "uuid-char")
    val uuid: UUID,
    val estado: TipoEstado,
    @Serializable(LocalDateSerializer::class)
    val fechaEntrada: LocalDate,
    @Serializable(LocalDateSerializer::class)
    val fechaSalidaProgramada: LocalDate,
    @Serializable(LocalDateSerializer::class)
    val fechaEntrega: LocalDate?,
    val precio: Double,
    @ManyToOne
    @JoinColumn(name = "usuario_uuid", referencedColumnName = "UUID_Usuario")
    val usuario:Usuario,

    ): java.io.Serializable