package repositories.MaquinaPersonalizacionRepository

import HibernateManager.manager
import models.Maquina
import mu.KotlinLogging
import repository.MaquinaPersonalizacionRepository.MaquinaPersonalizacionRepository
import java.util.*
import javax.persistence.TypedQuery

private val logger = KotlinLogging.logger {}

class MaquinaPersonalizacionRepositoryImpl:MaquinaPersonalizacionRepository {

    override fun findAll(): List<Maquina.MaquinaPersonalizacion> {
        logger.debug { "findAll()" }
        var maquinasPerso = mutableListOf<Maquina.MaquinaPersonalizacion>()
        HibernateManager.query {
            val query: TypedQuery<Maquina.MaquinaPersonalizacion> = manager.createNamedQuery("MaquinaPer.findAll", Maquina.MaquinaPersonalizacion::class.java)
            maquinasPerso = query.resultList
        }
        return maquinasPerso
    }

    override fun findById(id: Int): Maquina.MaquinaPersonalizacion? {
        logger.debug { "findById($id)" }
        var maquinaPerso: Maquina.MaquinaPersonalizacion? = null
        HibernateManager.query {
            maquinaPerso = manager.find(Maquina.MaquinaPersonalizacion::class.java, id)
        }
        return maquinaPerso
    }

    override fun findbyUUID(uuid: UUID): Maquina.MaquinaPersonalizacion? {
        logger.debug { "findByuuid($uuid)" }
        var maquinaPerso: Maquina.MaquinaPersonalizacion? = null
        HibernateManager.query {
            val query:TypedQuery<Maquina.MaquinaPersonalizacion> = manager.createNamedQuery("MaquinaPerson.porNumSerie",  Maquina.MaquinaPersonalizacion::class.java)
            maquinaPerso=query.singleResult
        }
        return maquinaPerso
    }

    override fun save(entity: Maquina.MaquinaPersonalizacion): Maquina.MaquinaPersonalizacion {
        logger.debug { "save($entity)" }
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Maquina.MaquinaPersonalizacion): Boolean {
        var result = false
        logger.debug { "delete($entity)" }
        HibernateManager.transaction {
            val maquinaPerso = manager.find(Maquina.MaquinaPersonalizacion::class.java, entity.id)//si no va cambiar a ID TODO MIRAR
            maquinaPerso?.let {
                manager.remove(it)
                result = true
            }
        }
        return result
    }
}