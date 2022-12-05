package models

import models.enums.TipoEstado
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
data class Pedidos(
    val id:Int,
    val uuid: UUID,
    val estado: TipoEstado,
    val fechaEntrada: LocalDate,
    val fechaSalidaProgramada: LocalDate,
    val fechaEntrega: LocalDate?,
    val precio: Double,


    )
