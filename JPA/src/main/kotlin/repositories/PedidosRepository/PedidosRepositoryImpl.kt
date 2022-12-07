package repositories.PedidosRepository

import HibernateManager.manager
import models.Pedidos
import models.Usuario
import mu.KotlinLogging
import repository.PedidosRepository.PedidosRepository
import java.util.*
import javax.persistence.TypedQuery

private val logger = KotlinLogging.logger {}

class PedidosRepositoryImpl:PedidosRepository {

    override fun findAll(): List<Pedidos> {
       logger.debug { "findAll()" }
        var pedidos = mutableListOf<Pedidos>()
        HibernateManager.query {
            val query: TypedQuery<Pedidos> = manager.createNamedQuery("Pedidos.findAll", Pedidos::class.java)
            pedidos = query.resultList
        }
        return pedidos
    }

    override fun findById(id: Int): Pedidos? {
        logger.debug { "findById($id)" }
        var pedido: Pedidos? = null
        HibernateManager.query {
            pedido = manager.find(Pedidos::class.java, id)
        }
        return pedido
    }

    override fun findbyUUID(uuid: UUID): Pedidos? {
        logger.debug { "findByuuid($uuid)" }
        var pedido: Pedidos? = null
        HibernateManager.query {
            val query: TypedQuery<Pedidos> = manager.createNamedQuery("Pedidos.porUUID", Pedidos::class.java)
            pedido=query.singleResult
        }
        return pedido
    }

    override fun save(entity: Pedidos): Pedidos {
       logger.debug { "save($entity)" }
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Pedidos): Boolean {
        var result = false
        logger.debug { "delete($entity)" }
        HibernateManager.transaction {
            val pedido = manager.find(Pedidos::class.java, entity.id)//si no va cambiar a ID TODO MIRAR
            pedido?.let {
                manager.remove(it)
                result = true
            }
        }
        return result
    }
}