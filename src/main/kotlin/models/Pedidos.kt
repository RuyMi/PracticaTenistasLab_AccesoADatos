package models

import models.enums.TipoEstado
import java.time.LocalDate
import java.util.UUID

data class Pedidos(
    val uuid: UUID,
    val estado: TipoEstado,
    val maquinaAsociada: Maquina?,
    val fechaEntrada: LocalDate,
    val fechaSalidaProgramada: LocalDate,
    val fechaEntrega: LocalDate,
    val precio: Double,
    val uuidEncordador: String
)
