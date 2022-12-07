package models

import kotlinx.serialization.Serializable
import models.enums.TipoEstado
import serializers.LocalDateSerializer
import serializers.UUIDSerializer
import java.time.LocalDate
import java.util.UUID

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
data class Pedidos(
    val id:Int,
    @Serializable(UUIDSerializer::class)
    val uuid: UUID,
    val estado: TipoEstado,
    @Serializable(LocalDateSerializer::class)
    val fechaEntrada: LocalDate,
    @Serializable(LocalDateSerializer::class)
    val fechaSalidaProgramada: LocalDate,
    @Serializable(LocalDateSerializer::class)
    val fechaEntrega: LocalDate?,
    val precio: Double,
    val usuario: Usuario


    )
