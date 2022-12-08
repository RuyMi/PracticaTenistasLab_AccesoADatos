package repositories.TurnosRepository

import HibernateManager.manager
import models.Turno
import mu.KotlinLogging

import repository.TurnosRepository.TurnosRepository
import java.util.*
import javax.persistence.TypedQuery

private val logger = KotlinLogging.logger {}

/**
 * Turnos repository impl
 *
 * @constructor Create empty Turnos repository impl
 */
class TurnosRepositoryImpl:TurnosRepository {

    override fun findAll(): List<Turno> {
        logger.debug { "findAll()" }
        var turnos = mutableListOf<Turno>()
        HibernateManager.query {
            val query: TypedQuery<Turno> = manager.createNamedQuery("Turnos.findAll", Turno::class.java)
            turnos = query.resultList
        }
        return turnos
    }

    override fun findById(id: Int): Turno? {
        logger.debug { "findById($id)" }
        var turno: Turno? = null
        HibernateManager.query {
            turno = manager.find(Turno::class.java, id)
        }
        return turno
    }

    override fun findbyUUID(uuid: UUID): Turno? {
        logger.debug { "findByuuid($uuid)" }
        var turno: Turno? = null
        HibernateManager.query {
            val query: TypedQuery<Turno> = manager.createNamedQuery("Turno.porUUID", Turno::class.java)
            query.setParameter("id", uuid)
            turno=query.singleResult
        }
        return turno
    }

    override fun save(entity: Turno): Turno {
       logger.debug { "save($entity)" }
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Turno): Boolean {
        var result = false
        logger.debug { "delete($entity)" }
        HibernateManager.transaction {
            val turno = manager.find(Turno::class.java, entity.id)//si no va cambiar a ID TODO MIRAR
            turno?.let {
                manager.remove(it)
                result = true
            }
        }
        return result
    }
}