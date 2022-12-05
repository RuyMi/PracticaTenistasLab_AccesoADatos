package entities



import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable


object TareaTable : IntIdTable("TAREA") {
    val uuidTarea = uuid("uuid_tarea").uniqueIndex()
    val producto = reference("uuid_producto", ProductoTable)
    val precio = double("precio")
    val descripcion=varchar("Descripcion",150)
    val empleado=reference("uuid_Usuario",UsuarioTable)
    val turno = reference("uuid_Turno",TurnoTable)
    val estadoCompletado=bool("EstadoCompletado")
    val maquinaEncordar = reference("numSerieEncordar", MaquinaEncordarTable).nullable()
    val maquinaPersonalizacion = reference("numSeriePersonalizar", MaquinaPersonalizacionTable).nullable()
    val pedido=reference("uuid_Pedido",PedidosTable)

}


//DAO de la entidad Producto


/**
 * Tarea dao
 *
 * @constructor
 *
 * @param id
 */
class TareaDao(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<TareaDao>(TareaTable)

    var uuidTarea by TareaTable.uuidTarea
    var producto by ProductoDao referencedOn TareaTable.producto
    var precio by TareaTable.precio
    var descripcion by TareaTable.descripcion
    var empleado by UsuarioDao referencedOn TareaTable.empleado
    var turno by TurnoDao referencedOn TareaTable.turno
    var estadoCompletado by TareaTable.estadoCompletado
    var maquinaEncordar by MaquinaEncordarDao optionalReferencedOn TareaTable.maquinaEncordar
    var maquinaPersonalizacion by MaquinaPersonalizacionDao optionalReferencedOn TareaTable.maquinaPersonalizacion
    var pedido by PedidosDao referencedOn TareaTable.pedido

}

