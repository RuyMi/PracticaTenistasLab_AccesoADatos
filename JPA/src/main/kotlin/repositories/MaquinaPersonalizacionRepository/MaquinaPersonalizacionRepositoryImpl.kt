package repositories.MaquinaPersonalizacionRepository

import HibernateManager.manager
import models.MaquinaPersonalizacion
import mu.KotlinLogging
import repository.MaquinaPersonalizacionRepository.MaquinaPersonalizacionRepository
import java.util.*
import javax.persistence.TypedQuery

private val logger = KotlinLogging.logger {}

/**
 * Maquina personalizacion repository impl
 *
 * @constructor Create empty Maquina personalizacion repository impl
 */
class MaquinaPersonalizacionRepositoryImpl:MaquinaPersonalizacionRepository {

    override fun findAll(): List<MaquinaPersonalizacion> {
        logger.debug { "findAll()" }
        var maquinasPerso = mutableListOf<MaquinaPersonalizacion>()
        HibernateManager.query {
            val query: TypedQuery<MaquinaPersonalizacion> = manager.createNamedQuery("MaquinaPer.findAll", MaquinaPersonalizacion::class.java)
            maquinasPerso = query.resultList
        }
        return maquinasPerso
    }

    override fun findById(id: Int): MaquinaPersonalizacion? {
        logger.debug { "findById($id)" }
        var maquinaPerso: MaquinaPersonalizacion? = null
        HibernateManager.query {
            maquinaPerso = manager.find(MaquinaPersonalizacion::class.java, id)
        }
        return maquinaPerso
    }

    override fun findbyUUID(uuid: UUID): MaquinaPersonalizacion? {
        logger.debug { "findByuuid($uuid)" }
        var maquinaPerso: MaquinaPersonalizacion? = null
        HibernateManager.query {
            val query:TypedQuery<MaquinaPersonalizacion> = manager.createNamedQuery("MaquinaPerson.porNumSerie",  MaquinaPersonalizacion::class.java)
            query.setParameter("id", uuid)
            maquinaPerso=query.singleResult
        }
        return maquinaPerso
    }

    override fun save(entity: MaquinaPersonalizacion): MaquinaPersonalizacion {
        logger.debug { "save($entity)" }
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: MaquinaPersonalizacion): Boolean {
        var result = false
        logger.debug { "delete($entity)" }
        HibernateManager.transaction {
            val maquinaPerso = manager.find(MaquinaPersonalizacion::class.java, entity.id)//si no va cambiar a ID TODO MIRAR
            maquinaPerso?.let {
                manager.remove(it)
                result = true
            }
        }
        return result
    }
}