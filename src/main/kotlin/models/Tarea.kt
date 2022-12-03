package models

import java.util.*

data class Tarea(
    val id: Int,
    val uuidTarea: UUID,
    val producto: Producto,
    val precio: Double,
    val descripcion: String,
    val maquinaEncordar: Maquina.MaquinaEncordadora?,
    val maquinaPersonalizacion: Maquina.MaquinaPersonalizacion?,
    val turno:Turno
) {
}
