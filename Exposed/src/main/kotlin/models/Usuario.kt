package models

import kotlinx.serialization.Serializable
import models.enums.TipoPerfil
import serializers.UUIDSerializer
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
@Serializable
data class Usuario(
    val id:Int,
    @Serializable(UUIDSerializer::class)
    val uuid: UUID,
    val nombre: String,
    val apellido: String,
    val email: String,
    val password: String,
    val perfil: TipoPerfil,
    val turno:Turno?,
) {

    override fun toString(): String {
        return "Usuario(id=$id, uuid=$uuid, nombre='$nombre', apellido='$apellido', perfil=$perfil, turno=$turno)"
    }
}
