package entities

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object ProductoTable : IntIdTable("PRODUCTO") {
    val uuid = uuid("uuid").uniqueIndex()
    val marca = varchar("marca", 100)
    val modelo = varchar("modelo", 100)
    val precio = double("precio")
    val stock = integer("perfil")
    //val id = integer("id").autoIncrement().entityId()

}


//DAO de la entidad Producto


class Producto(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Producto>(ProductoTable)

    var uuid by ProductoTable.uuid
    var marca by ProductoTable.marca
    var modelo by ProductoTable.modelo
    var precio by ProductoTable.precio
    var stock by ProductoTable.stock
}



