package repositories.ProductosRepository

import HibernateManager.manager
import models.Producto
import mu.KotlinLogging

import repository.ProductosRepository.ProductosRepository
import java.util.*
import javax.persistence.TypedQuery

private val logger = KotlinLogging.logger {}

class ProductosRepositoryImpl : ProductosRepository {

    override fun findAll(): List<Producto> {
        logger.debug { "findAll()" }
        var productos = mutableListOf<Producto>()
        HibernateManager.query {
            val query: TypedQuery<Producto> = manager.createNamedQuery("Productos.findAll", Producto::class.java)
            productos = query.resultList
        }
        return productos
    }

    override fun findById(id: Int): Producto? {
       logger.debug { "findById($id)" }
        var producto: Producto? = null
        HibernateManager.query {
            producto = manager.find(Producto::class.java, id)
        }
        return producto
    }

    override fun findbyUUID(uuid: UUID): Producto? {
        logger.debug { "findByuuid($uuid)" }
        var producto: Producto? = null
        HibernateManager.query {
            val query: TypedQuery<Producto> = manager.createNamedQuery("Producto.porUUID", Producto::class.java)
            query.setParameter("id", uuid)
            producto=query.singleResult
        }
        return producto
    }

    override fun save(entity: Producto): Producto {
        logger.debug { "save($entity)" }
        HibernateManager.transaction {
            manager.merge(entity)
        }
        return entity
    }

    override fun delete(entity: Producto): Boolean {
        var result = false
       logger.debug { "delete($entity)" }
        HibernateManager.transaction {
            val producto = manager.find(Producto::class.java, entity.id)//si no va cambiar a ID TODO MIRAR
            producto?.let {
                manager.remove(it)
                result = true
            }
        }
        return result
    }
}