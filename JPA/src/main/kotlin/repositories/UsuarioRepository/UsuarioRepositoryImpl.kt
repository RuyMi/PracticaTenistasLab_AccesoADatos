package repositories.UsuarioRepository

import HibernateManager.manager
import models.Usuario
import mu.KotlinLogging
import repository.UsuarioRepository.UsuarioRepository
import java.util.*
import javax.persistence.TypedQuery

private val logger = KotlinLogging.logger {}

/**
 * Usuario repository impl
 *
 * @constructor Create empty Usuario repository impl
 */
class UsuarioRepositoryImpl: UsuarioRepository {


    override fun findAll(): List<Usuario> {
        logger.debug { "findAll()" }
        var usuarios = mutableListOf<Usuario>()
        HibernateManager.query {
            val query: TypedQuery<Usuario> = manager.createNamedQuery("Usuarios.findAll", Usuario::class.java)
            usuarios = query.resultList
        }
        return usuarios
    }

    override fun findById(id: Int): Usuario? {
        logger.debug { "findById($id)" }
        var usuario: Usuario? = null
        HibernateManager.query {
            usuario = manager.find(Usuario::class.java, id)
        }
        return usuario
    }

    override fun findbyUUID(uuid: UUID): Usuario? {
        logger.debug { "findByuuid($uuid)" }
        var usuario: Usuario? = null
        HibernateManager.query {
            val query: TypedQuery<Usuario> = manager.createNamedQuery("Usuario.porUUID", Usuario::class.java)
            query.setParameter("uuid", uuid)
            usuario=query.singleResult
        }
        return usuario
    }

    override fun save(entity: Usuario): Usuario {
        logger.debug { "save($entity)" }
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Usuario): Boolean {
        var result = false
        logger.debug { "delete($entity)" }
        HibernateManager.transaction {
            val usuario = manager.find(Usuario::class.java, entity.id)
            usuario?.let {
                manager.remove(it)
                result = true
            }
        }
        return result
    }
}