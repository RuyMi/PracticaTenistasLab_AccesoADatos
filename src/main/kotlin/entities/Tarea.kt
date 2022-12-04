package entities



import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable


object TareaTable : IntIdTable("TAREA") {
    val uuidTarea = uuid("uuid_tarea").uniqueIndex()
    val producto = reference("uuid_producto", ProductoTable).nullable()
    val precio = double("precio")
    val descripcion=varchar("Descripcion",150)
    val empleado=reference("uuid_Usuario",UsuarioTable)
    val turno = reference("uuid_Turno",TurnoTable)
    val estadoCompletado=bool("EstadoCompletado")
    val maquinaEncordar = reference("numSerie", MaquinaEncordarTable).nullable()
    val maquinaPersonalizacion = reference("numSerie", MaquinaPersonalizacionTable).nullable()

}


//DAO de la entidad Producto


class TareaDao(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<TareaDao>(TareaTable)

    var uuidTarea by TareaTable.uuidTarea
    val producto by ProductoDao backReferencedOn TareaTable.producto
    var precio by TareaTable.precio
    var descripcion by TareaTable.descripcion
    val empleado by UsuarioDao backReferencedOn TareaTable.empleado
    val turno by TurnoDao backReferencedOn TareaTable.turno
    var estadoCompletado by TareaTable.estadoCompletado
    val maquinaEncordar by MaquinaEncordarDao backReferencedOn TareaTable.maquinaEncordar
    val maquinaPersonalizacion by MaquinaPersonalizacionDao backReferencedOn TareaTable.maquinaPersonalizacion

}

