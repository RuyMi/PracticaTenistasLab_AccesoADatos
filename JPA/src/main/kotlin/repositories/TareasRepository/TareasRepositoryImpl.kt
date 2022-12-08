package repositories.TareasRepository

import HibernateManager.manager
import models.Tarea
import mu.KotlinLogging
import repository.TareasRepository.TareasRepository
import java.util.*
import javax.persistence.TypedQuery

private val logger = KotlinLogging.logger {}

/**
 * Tareas repository impl
 *
 * @constructor Create empty Tareas repository impl
 */
class TareasRepositoryImpl : TareasRepository {

    override fun findAll(): List<Tarea> {
        logger.debug { "findAll()" }
        var tareas = mutableListOf<Tarea>()
        HibernateManager.query {
            val query: TypedQuery<Tarea> = manager.createNamedQuery("Tareas.findAll", Tarea::class.java)
            tareas = query.resultList
        }
        return tareas
    }

    override fun findById(id: Int): Tarea? {
        logger.debug { "findById($id)" }
        var tarea: Tarea? = null
        HibernateManager.query {
            tarea = manager.find(Tarea::class.java, id)
        }
        return tarea
    }

    override fun findbyUUID(uuid: UUID): Tarea? {
        logger.debug { "findByuuid($uuid)" }
        var tarea: Tarea? = null
        HibernateManager.query {
            val query: TypedQuery<Tarea> = manager.createNamedQuery("Tareas.porUUID", Tarea::class.java)
            query.setParameter("id", uuid)
            tarea=query.singleResult
        }
        return tarea
    }

    override fun save(entity: Tarea): Tarea {
        logger.debug { "save($entity)" }
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Tarea): Boolean {
        var result = false
        logger.debug { "delete($entity)" }
        HibernateManager.transaction {
            val tarea = manager.find(Tarea::class.java, entity.id)
            tarea?.let {
                manager.remove(it)
                result = true
            }
        }
        return result
    }
}