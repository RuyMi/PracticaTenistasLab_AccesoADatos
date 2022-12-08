package repository.UsuarioRepository

import entities.*
import exceptions.MaquinaException
import exceptions.TurnoException
import mappers.fromPedidosDaoToPedidos
import mappers.fromUsuarioDaoToUsuario
import models.Pedidos
import models.Usuario
import mu.KotlinLogging
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

import java.util.*

private val logger = KotlinLogging.logger {}

/**
 * Usuario repository impl
 *
 * @property usuarioDao
 * @constructor Create empty Usuario repository impl
 */
class UsuarioRepositoryImpl(
    private val usuarioDao: IntEntityClass<UsuarioDao>
    ) : UsuarioRepository {

    override fun findAll(): List<Usuario> = transaction {
        logger.debug { "findAll()" }
        usuarioDao.all().map { it.fromUsuarioDaoToUsuario() }
    }

    override fun findById(id: Int): Usuario? = transaction {
       logger.debug { "findAll()" }
        usuarioDao.findById(id)?.fromUsuarioDaoToUsuario()
    }

    override fun findbyUUID(uuid: UUID): Usuario? = transaction {
        logger.debug { "findById($uuid)" }
        val usuarios = UsuarioDao.find { UsuarioTable.uuid eq uuid }
        return@transaction usuarios.first().fromUsuarioDaoToUsuario()
    }

    override fun save(entity: Usuario): Usuario = transaction {
        val existe = usuarioDao.findById(entity.id)
        existe?.let {
            update(entity, existe)
        } ?: run {
            insert(entity)
        }
    }

    override fun delete(entity: Usuario): Boolean = transaction {
        val existe = usuarioDao.findById(entity.id) ?: return@transaction false
        // Si existe lo borramos
        logger.debug { "delete($entity) - borrando" }
        existe.delete()
        true
    }

    private fun insert(entity: Usuario): Usuario {
       logger.debug { "save($entity) - creando" }
        return usuarioDao.new() {
            uuid = entity.uuid
            nombre=entity.nombre
            apellido=entity.apellido
            email=entity.email
            password=entity.password
            perfil=entity.perfil
            turno= entity.turno?.let { TurnoDao.findById(it.id)?: throw TurnoException("El turno no existe con id: ${entity.turno.id}") }
        }.fromUsuarioDaoToUsuario()
    }

    private fun update(entity: Usuario, existe: UsuarioDao): Usuario {
        logger.debug { "save($entity) - actualizando" }
        // Si existe actualizamos
        return existe.apply {
            uuid = entity.uuid
            nombre=entity.nombre
            apellido=entity.apellido
            email=entity.email
            password=entity.password
            perfil=entity.perfil
            turno= entity.turno?.let { TurnoDao.findById(it.id)?: throw TurnoException("El turno no existe con id: ${entity.turno.id}") }
        }.fromUsuarioDaoToUsuario()
    }


}
