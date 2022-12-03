package models

import entities.UsuarioDao
import java.util.UUID

data class Turno(
    val id :Int,
    val uuid:UUID,
    val tarea:Tarea,
    val empleado:Usuario,
) {
}