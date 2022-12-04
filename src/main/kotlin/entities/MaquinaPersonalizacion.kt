package entities



import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

import org.jetbrains.exposed.dao.id.UUIDTable

import org.jetbrains.exposed.sql.javatime.date
import java.util.*

object MaquinaPersonalizacionTable : IntIdTable("MAQUINAPERSO") {

    val numSerie = uuid("numSeriePersonalizar").uniqueIndex()
    val marca = varchar("marca", 100)
    val modelo = varchar("modelo", 100)
    val fechaAdquisicion = date("fechaAdquisicion")
    val swingweight= bool("swingweitght")//se puede medir o no la maniobrabilidad
    val balance= double("balance")
    val rigidez=double("rigidez")
}
class MaquinaPersonalizacionDao(id: EntityID<Int>):  IntEntity(id) {

    companion object : IntEntityClass<MaquinaPersonalizacionDao>(MaquinaPersonalizacionTable)

    var numSerie by MaquinaPersonalizacionTable.numSerie
    var marca by MaquinaPersonalizacionTable.marca
    var modelo by MaquinaPersonalizacionTable.modelo
    var fechaAdquisicion by MaquinaPersonalizacionTable.fechaAdquisicion
    var swingweight by MaquinaPersonalizacionTable.swingweight
    var balance by MaquinaPersonalizacionTable.balance
    var rigidez by MaquinaPersonalizacionTable.rigidez
}