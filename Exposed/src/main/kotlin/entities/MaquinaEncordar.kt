package entities

import entities.ProductoTable.uniqueIndex
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

import org.jetbrains.exposed.sql.javatime.date
import java.util.*

object MaquinaEncordarTable : IntIdTable("MAQUINAENCORDAR") {
    val numSerie = uuid("numSerieEncordar").uniqueIndex().default(UUID.randomUUID())
    val marca = varchar("marca", 100)
    val modelo = varchar("modelo", 100)
    val fechaAdquisicion = date("fechaAdquisicion")
    val automatico = bool("automatico")
    val tensionMaxima = double("tensionMaxima")
    val tensionMinima = double("tensionMinima")


}
/**
 * Maquina encordar dao
 *
 * @constructor
 *
 * @param id
 */
class MaquinaEncordarDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MaquinaEncordarDao>(MaquinaEncordarTable)

    var numSerie by MaquinaEncordarTable.numSerie
    var marca by MaquinaEncordarTable.marca
    var modelo by MaquinaEncordarTable.modelo
    var fechaAdquisicion by MaquinaEncordarTable.fechaAdquisicion
    var automatico by MaquinaEncordarTable.automatico
    var tensionMaxima by MaquinaEncordarTable.tensionMaxima
    var tensionMinima by MaquinaEncordarTable.tensionMinima


}