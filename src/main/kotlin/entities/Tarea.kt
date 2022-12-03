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

object TareaTable : IntIdTable("TAREA") {
    val uuid = uuid("uuid").uniqueIndex()
    val raqueta = reference("uuid_producto", ProductoTable)
    val precio = double("precio")
    val maquinaEncordar = reference("uuid_maquinaE", MaquinaEncordarTable).nullable()
    val maquinaPersonalizacion = reference("uuid_maquinaP", MaquinaPersonalizacionTable).nullable()




}


//DAO de la entidad Producto


class TareaDao(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<TareaDao>(TareaTable)

    var uuid by TareaTable.uuid
    var raqueta by TareaTable.raqueta
    var precio by TareaTable.precio

    val maquinaPersonalizacion by TareaDao optionalReferrersOn PedidosTable.tareas
    val maquinaEncordar by TareaDao optionalReferrersOn TareaTable.maquinaEncordar




}

