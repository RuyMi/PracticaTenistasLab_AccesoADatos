package repositories.MaquinaEncordarRepository

import HibernateManager.manager
import models.MaquinaEncordadora
import mu.KotlinLogging

import repository.MaquinaEncordarRepository.MaquinaEncordarRepository
import java.util.*
import javax.persistence.TypedQuery


private val logger = KotlinLogging.logger {}

class MaquinaEncordarRepositoryImpl :MaquinaEncordarRepository{

    override fun findAll(): List<MaquinaEncordadora> {
        logger.debug { "findAll()" }
        var maquinasPerso = mutableListOf<MaquinaEncordadora>()
        HibernateManager.query {
            val query: TypedQuery<MaquinaEncordadora> = manager.createNamedQuery("MaquinaEncor.findAll", MaquinaEncordadora::class.java)
            maquinasPerso = query.resultList
        }
        return maquinasPerso
    }

    override fun findById(id: Int): MaquinaEncordadora? {
        logger.debug { "findById($id)" }
        var maquinaEncordar: MaquinaEncordadora? = null
        HibernateManager.query {
            maquinaEncordar = manager.find(MaquinaEncordadora::class.java, id)
        }
        return maquinaEncordar
    }

    override fun findbyUUID(uuid: UUID): MaquinaEncordadora? {
        logger.debug { "findByuuid($uuid)" }
        var maquinaEncordar: MaquinaEncordadora? = null
        HibernateManager.query {
            val query:TypedQuery<MaquinaEncordadora> = manager.createNamedQuery("MaquinaEncor.porNumSerie",  MaquinaEncordadora::class.java)
            maquinaEncordar=query.singleResult
        }
        return maquinaEncordar
    }

    override fun save(entity: MaquinaEncordadora): MaquinaEncordadora {
        logger.debug { "save($entity)" }
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: MaquinaEncordadora): Boolean {
        var result = false
        logger.debug { "delete($entity)" }
        HibernateManager.transaction {
            val maquinaEncordar = manager.find(MaquinaEncordadora::class.java, entity.id)//si no va cambiar a ID TODO MIRAR
            maquinaEncordar?.let {
                manager.remove(it)
                result = true
            }
        }
        return result
    }
}