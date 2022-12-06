package models

import kotlinx.serialization.Serializable
import serializers.UUIDSerializer
import java.util.*


/**
 * Tarea
 *
 * @property id
 * @property uuidTarea
 * @property producto
 * @property precio
 * @property descripcion
 * @property empleado
 * @property turno
 * @property estadoCompletado
 * @property maquinaEncordar
 * @property maquinaPersonalizacion
 * @property pedido
 * @constructor Create empty Tarea
 */
@Serializable
data class Tarea(
    val id: Int,
    @Serializable(UUIDSerializer::class)
    val uuidTarea: UUID,
    val producto: Producto,
    val precio: Double,
    val descripcion: String,
    val empleado:Usuario,
    val turno:Turno,
    val estadoCompletado:Boolean,
    val maquinaEncordar: Maquina.MaquinaEncordadora?,
    val maquinaPersonalizacion: Maquina.MaquinaPersonalizacion?,
    val pedido:Pedidos
) {
    override fun toString(): String {
        return "Tarea(id=$id, uuidTarea=$uuidTarea, producto=$producto, precio=$precio, descripcion='$descripcion', empleado=${empleado.nombre}, ${empleado.apellido} turno=$turno, estadoCompletado=$estadoCompletado, maquinaEncordar=$maquinaEncordar, maquinaPersonalizacion=$maquinaPersonalizacion, pedido=$pedido)"
    }
}
