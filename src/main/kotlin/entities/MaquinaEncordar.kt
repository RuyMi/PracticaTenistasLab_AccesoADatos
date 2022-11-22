package entities

import entities.ProductoTable.uniqueIndex
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

import org.jetbrains.exposed.sql.javatime.date

object MaquinaEncordarTable : IntIdTable("PRODUCTO") {
    val numSerie = uuid("numSerie").uniqueIndex()
    val marca = varchar("marca", 100)
    val modelo = varchar("modelo", 100)
    val fechaAdquisicion = date("fechaAdquisicion")
    val automatico = bool("automatico")
    val tensionMaxima = double("tensionMaxima")
    val tensionMinima = double("tensionMinima")
    //val id = integer("id").autoIncrement().entityId()

}


//DAO de la entidad Producto


class MaquinaEncordarDao(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<MaquinaEncordarDao>(MaquinaEncordarTable)

    var uuid by ProductoTable.uuid
    var marca by ProductoTable.marca
    var modelo by ProductoTable.modelo
    var precio by ProductoTable.precio
    var stock by ProductoTable.stock
}
