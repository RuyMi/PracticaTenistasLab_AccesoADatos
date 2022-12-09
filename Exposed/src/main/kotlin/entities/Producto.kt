package entities

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object ProductoTable : IntIdTable("PRODUCTO") {
    val uuid = uuid("uuid_producto").uniqueIndex()
    val marca = varchar("marca", 100)
    val modelo = varchar("modelo", 100)
    val precio = double("precio")
    val stock = integer("stock")
}

/**
 * Producto dao
 *
 * @constructor
 *
 * @param id
 */
class ProductoDao(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<ProductoDao>(ProductoTable)

    var uuid by ProductoTable.uuid
    var marca by ProductoTable.marca
    var modelo by ProductoTable.modelo
    var precio by ProductoTable.precio
    var stock by ProductoTable.stock

}



