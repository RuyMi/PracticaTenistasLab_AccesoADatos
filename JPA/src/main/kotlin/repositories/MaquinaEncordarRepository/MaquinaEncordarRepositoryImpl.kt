package repositories.MaquinaEncordarRepository

import HibernateManager.manager
import models.Maquina
import mu.KotlinLogging

import repository.MaquinaEncordarRepository.MaquinaEncordarRepository
import java.util.*
import javax.persistence.TypedQuery


private val logger = KotlinLogging.logger {}

class MaquinaEncordarRepositoryImpl :MaquinaEncordarRepository{

    override fun findAll(): List<Maquina.MaquinaEncordadora> {
        logger.debug { "findAll()" }
        var maquinasPerso = mutableListOf<Maquina.MaquinaEncordadora>()
        HibernateManager.query {
            val query: TypedQuery<Maquina.MaquinaEncordadora> = manager.createNamedQuery("MaquinaEncor.findAll", Maquina.MaquinaEncordadora::class.java)
            maquinasPerso = query.resultList
        }
        return maquinasPerso
    }

    override fun findById(id: Int): Maquina.MaquinaEncordadora? {
        logger.debug { "findById($id)" }
        var maquinaEncordar: Maquina.MaquinaEncordadora? = null
        HibernateManager.query {
            maquinaEncordar = manager.find(Maquina.MaquinaEncordadora::class.java, id)
        }
        return maquinaEncordar
    }

    override fun findbyUUID(uuid: UUID): Maquina.MaquinaEncordadora? {
        logger.debug { "findByuuid($uuid)" }
        var maquinaEncordar: Maquina.MaquinaEncordadora? = null
        HibernateManager.query {
            val query:TypedQuery<Maquina.MaquinaEncordadora> = manager.createNamedQuery("MaquinaEncor.porNumSerie",  Maquina.MaquinaEncordadora::class.java)
            maquinaEncordar=query.singleResult
        }
        return maquinaEncordar
    }

    override fun save(entity: Maquina.MaquinaEncordadora): Maquina.MaquinaEncordadora {
        logger.debug { "save($entity)" }
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Maquina.MaquinaEncordadora): Boolean {
        var result = false
        logger.debug { "delete($entity)" }
        HibernateManager.transaction {
            val maquinaEncordar = manager.find(Maquina.MaquinaEncordadora::class.java, entity.id)//si no va cambiar a ID TODO MIRAR
            maquinaEncordar?.let {
                manager.remove(it)
                result = true
            }
        }
        return result
    }
}