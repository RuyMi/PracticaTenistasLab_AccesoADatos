package repository.PedidosRepository

import entities.*
import mappers.fromMaquinaPersonalizacionDaoToMaquinaPersonalizacion
import mappers.fromPedidosDaoToPedidos
import mappers.fromTareaDaoToTarea
import models.Maquina
import models.Pedidos
import mu.KotlinLogging
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import kotlin.jvm.Throws


private val logger = KotlinLogging.logger {}

/**
 * Pedidos repository impl
 *
 * @property pedidoDao
 * @property tareasDao
 * @constructor Create empty Pedidos repository impl
 */
class PedidosRepositoryImpl(
    private val pedidoDao: IntEntityClass<PedidosDao>,
    private val tareasDao: IntEntityClass<TareaDao>
) : PedidosRepository {

    override fun findAll(): List<Pedidos> = transaction {
        logger.debug { "findAll()" }
        pedidoDao.all().map { it.fromPedidosDaoToPedidos() }
    }

    override fun findById(id: Int): Pedidos? = transaction {
        logger.debug { "findAll()" }
        pedidoDao.findById(id)?.fromPedidosDaoToPedidos()
    }

    override fun findbyUUID(uuid: UUID): Pedidos? = transaction {
        logger.debug { "findById($uuid)" }
        val pedidos = PedidosDao.find { PedidosTable.uuid eq uuid }
        return@transaction pedidos.first().fromPedidosDaoToPedidos()
    }

    override fun save(entity: Pedidos): Pedidos = transaction {
        val existe = pedidoDao.findById(entity.id)
        existe?.let {
            update(entity, existe)
        } ?: run {
            insert(entity)
        }
    }

    override fun delete(entity: Pedidos): Boolean = transaction {
        val existe = pedidoDao.findById(entity.id) ?: return@transaction false
        // Si existe lo borramos
        logger.debug { "delete($entity) - borrando" }
        existe.delete()
        true
    }


    private fun insert(entity: Pedidos): Pedidos {
        logger.debug { "save($entity) - creando" }
            return pedidoDao.new() {
                uuid = entity.uuid
                estado = entity.estado
                fechaEntrada = entity.fechaEntrada
                fechaSalidaProgramada = entity.fechaSalidaProgramada
                fechaEntrega = entity.fechaEntrega
                precio = entity.precio
            }.fromPedidosDaoToPedidos()
        }

    private fun update(entity: Pedidos, existe: PedidosDao): Pedidos {
        logger.debug { "save($entity) - actualizando" }
        // Si existe actualizamos
            return existe.apply {
                uuid = entity.uuid
                estado = entity.estado
                fechaEntrada = entity.fechaEntrada
                fechaSalidaProgramada = entity.fechaSalidaProgramada
                fechaEntrega = entity.fechaEntrega
                precio = entity.precio
            }.fromPedidosDaoToPedidos()
    }
}