package models

import models.enums.TipoPerfil
import java.util.UUID

data class Usuario(
    val uuid: UUID,
    val nombre: String,
    val apellido: String,
    val email: String,
    val password: String,
    val perfil: TipoPerfil
) {

}