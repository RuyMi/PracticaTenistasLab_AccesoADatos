package models

import kotlinx.serialization.Serializable
import models.enums.TipoPerfil
import org.hibernate.annotations.Type
import serializers.UUIDSerializer
import java.util.*
import javax.persistence.*


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
@Entity
@Table(name = "Usuarios")
@NamedQueries(
    NamedQuery(name = "Usuarios.findAll", query = "SELECT t FROM Usuario t"),
    NamedQuery(
        name = "Usuario.porUUID",
        query = "SELECT t FROM Usuario t WHERE t.uuid = t"
    ),
)
data class Usuario(
    @Id @GeneratedValue
    val id:Int,
    @Serializable(UUIDSerializer::class)
    @Column(name="UUID_Usuario")
    @Type(type = "uuid-char")
    val uuid: UUID,
    val nombre: String,
    val apellido: String,
    val email: String,
    val password: String,
    val perfil: TipoPerfil,
    @OneToOne
    val turno:Turno?,
) {

    override fun toString(): String {
        return "Usuario(id=$id, uuid=$uuid, nombre='$nombre', apellido='$apellido', perfil=$perfil, turno=$turno)"
    }
}