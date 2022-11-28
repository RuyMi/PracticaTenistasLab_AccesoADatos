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

object TurnoTable : IntIdTable("PRODUCTO") {
    val uuid = uuid("uuid").uniqueIndex()
    val tareas = reference("uuid", TareaTable).nullable()
    val empleado = reference("uuid", UsuarioTable).nullable()




}


//DAO de la entidad Producto


class TurnoDao(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<TurnoDao>(TurnoTable)

    var uuid by TareaTable.uuid

    val tareas by TareaDao optionalReferrersOn TurnoTable.tareas
    val empleado by TareaDao optionalReferrersOn TurnoTable.empleado




}

