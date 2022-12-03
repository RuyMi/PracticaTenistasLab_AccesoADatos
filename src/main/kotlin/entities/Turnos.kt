package entities

import entities.PedidosDao.Companion.optionalReferrersOn
import entities.ProductoTable.uniqueIndex
import models.enums.TipoEstado
import models.enums.TipoPerfil
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date

object TurnoTable : IntIdTable("TURNOS") {
    val uuid = uuid("uuid")
    val tareas = reference("uuid_tarea", TareaTable)
    val empleado = reference("uuid_empleado", UsuarioTable)




}


//DAO de la entidad Producto


class TurnoDao(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<TurnoDao>(TurnoTable)

    var uuid by TurnoTable.uuid

    val tareas by TareaDao backReferencedOn  TurnoTable.tareas
    val empleado by UsuarioDao backReferencedOn TurnoTable.empleado



}

