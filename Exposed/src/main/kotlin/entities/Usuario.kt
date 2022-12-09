package entities

import models.enums.TipoPerfil
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object UsuarioTable : IntIdTable("USUARIO") {
    val uuid = uuid("uuid_Usuario").uniqueIndex()
    val nombre = varchar("nombre", 100)
    val apellido = varchar("apellido", 100)
    val email = varchar("email", 100)
    val password = varchar("password", 100)
    val perfil = enumeration<TipoPerfil>("perfil")
    val turno = reference("uuidTurno", TurnoTable).nullable()
}

/**
 * Usuario dao
 *
 * @constructor
 *
 * @param id
 */
class UsuarioDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UsuarioDao>(UsuarioTable)

    var uuid by UsuarioTable.uuid
    var nombre by UsuarioTable.nombre
    var apellido by UsuarioTable.apellido
    var email by UsuarioTable.email
    var password by UsuarioTable.password
    var perfil by UsuarioTable.perfil
    var turno by TurnoDao optionalReferencedOn UsuarioTable.turno


}