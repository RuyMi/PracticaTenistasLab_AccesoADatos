package entities

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.datetime

import java.time.LocalDateTime

object TurnoTable : IntIdTable("TURNOS") {
    val uuidTurno = uuid("uuid_Turno")
    val fechaInicio = datetime("FechaInicio")
    val fechaFin = datetime("FechaFin")
}

/**
 * Turno dao
 *
 * @constructor
 *
 * @param id
 */
class TurnoDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TurnoDao>(TurnoTable)
    var uuidTurno by TurnoTable.uuidTurno
    var fechaInicio by TurnoTable.fechaInicio
    var fechaFin by TurnoTable.fechaFin
}

