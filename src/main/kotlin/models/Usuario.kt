package models

import models.enums.TipoPerfil
import java.util.UUID

/**
 * Usuario
 *
 * @property id
 * @property uuid
 * @property nombre
 * @property apellido
 * @property email
 * @property password
 * @property perfil
 * @property turno
 * @constructor Create empty Usuario
 */
data class Usuario(
    val id:Int,
    val uuid: UUID,
    val nombre: String,
    val apellido: String,
    val email: String,
    val password: String,
    val perfil: TipoPerfil,
    val turno:Turno?,
) {

}
