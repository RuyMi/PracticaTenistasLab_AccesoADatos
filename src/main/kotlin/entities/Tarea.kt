package entities


import entities.MaquinaPersonalizacionDao.Companion.backReferencedOn
import entities.MaquinaPersonalizacionDao.Companion.optionalReferrersOn
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Table.Dual.references


object TareaTable : IntIdTable("TAREA") {
    val uuidTarea = uuid("uuid").uniqueIndex()
    val producto = reference("uuid_producto", ProductoTable).nullable()
    val precio = double("precio")
    val descripcion=varchar("Descripcion",150)
    val maquinaEncordar = reference("numSerie", MaquinaEncordarTable).nullable()
    val maquinaPersonalizacion = reference("numSerie", MaquinaPersonalizacionTable).nullable()
    val turno=reference("uuid_turno",TurnoTable)

}


//DAO de la entidad Producto


class TareaDao(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<TareaDao>(TareaTable)

    var uuidTarea by TareaTable.uuidTarea
    val producto by ProductoDao backReferencedOn TareaTable.producto
    var precio by TareaTable.precio
    var descripcion by TareaTable.descripcion

    val maquinaEncordar by MaquinaEncordarDao backReferencedOn TareaTable.maquinaEncordar
    val maquinaPersonalizacion by MaquinaPersonalizacionDao backReferencedOn TareaTable.maquinaPersonalizacion





}

