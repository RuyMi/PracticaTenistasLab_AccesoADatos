package models

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
data class Tarea(
    val id: Int,
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
}
