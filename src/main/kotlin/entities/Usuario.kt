package entities

import models.enums.TipoPerfil
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Table

object UsuarioTable : IntIdTable("USUARIO") {
    val uuid = uuid("uuid").uniqueIndex()
    val nombre = varchar("nombre", 100)
    val apellido = varchar("apellido", 100)
    val email = varchar("apellido", 100)
    val password = varchar("apellido", 100)
    val perfil = enumeration<TipoPerfil>("perfil")
    //val id = integer("id").autoIncrement().entityId()

}


class Usuario(id: EntityID<Int>): IntEntity(id){
    companion object : IntEntityClass<Usuario>(UsuarioTable)
    var uuid by UsuarioTable.uuid
    var nombre by UsuarioTable.nombre
    var apellido by UsuarioTable.apellido
    var email by UsuarioTable.email
    var password by UsuarioTable.password
    var perfil by UsuarioTable.perfil




}