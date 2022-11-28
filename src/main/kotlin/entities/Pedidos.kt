package entities

import entities.ProductoTable.uniqueIndex
import models.enums.TipoEstado
import models.enums.TipoPerfil
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date

object PedidosTable : IntIdTable("PRODUCTO") {
    val uuid = uuid("uuid").uniqueIndex()
    val estado = enumeration<TipoEstado>("estado")
    val fechaEntrada = date("fechaEntrada")
    val fechaSalidaProgramada = date("fechaSalidaProgramada")
    val fechaEntrega = date("fechaEntrega")
    val precio = double("precio")
    val tareas = reference("uuid", TareaTable).nullable()
    val productos = reference("uuid", ProductoTable)


    //val id = integer("id").autoIncrement().entityId()

}


//DAO de la entidad Producto


class PedidosDao(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<PedidosDao>(PedidosTable)

    var uuid by PedidosTable.uuid
    var estado by PedidosTable.estado
    var fechaEntrada by PedidosTable.fechaEntrada
    var fechaSalidaProgramada by PedidosTable.fechaSalidaProgramada
    var fechaEntrega by PedidosTable.fechaEntrega
    var precio by PedidosTable.precio

    val tareas by TareaDao optionalReferrersOn PedidosTable.tareas

}

