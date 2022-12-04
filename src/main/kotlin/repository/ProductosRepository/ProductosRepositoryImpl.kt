package repository.ProductosRepository

import entities.*
import mappers.fromProductoDaoToProducto
import mappers.fromUsuarioDaoToUsuario
import models.Producto
import models.Usuario
import mu.KotlinLogging
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

import java.util.*

private val logger = KotlinLogging.logger {}

class ProductosRepositoryImpl(
    private val productoDao: IntEntityClass<ProductoDao>
):ProductosRepository {
    override fun findAll(): List<Producto> = transaction{
        logger.debug { "findAll()" }
        productoDao.all().map { it.fromProductoDaoToProducto() }
    }

    override fun findById(id: Int): Producto? = transaction {
       logger.debug { "findAll()" }
        productoDao.findById(id)?.fromProductoDaoToProducto()
    }

    override fun findbyUUID(uuid: UUID): Producto? = transaction {
        logger.debug { "findById($uuid)" }
        val productos = ProductoDao.find { ProductoTable.uuid eq uuid }
        return@transaction productos.first().fromProductoDaoToProducto()
    }

    override fun save(entity: Producto): Producto  = transaction{
        TODO("Not yet implemented")
    }

    override fun delete(entity: Producto): Boolean = transaction {
        val existe = productoDao.findById(entity.id) ?: return@transaction false
        // Si existe lo borramos
        logger.debug { "delete($entity) - borrando" }
        existe.delete()
        true
    }



    private fun insert(entity: Producto): Producto {
        logger.debug { "save($entity) - creando" }
        return productoDao.new() {
            uuid=entity.uuid
            marca=entity.marca
            modelo=entity.modelo
            precio=entity.precio
            stock=entity.stock

        }.fromProductoDaoToProducto()
    }

    private fun update(entity: Producto, existe: ProductoDao): Producto {
       logger.debug { "save($entity) - actualizando" }
        // Si existe actualizamos
        return existe.apply {
            uuid = entity.uuid
            marca=entity.marca
            modelo=entity.modelo
            precio=entity.precio
            stock=entity.stock
        }.fromProductoDaoToProducto()
    }
}