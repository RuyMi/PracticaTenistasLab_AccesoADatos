package repository.TareasRepository

import entities.*
import exceptions.*
import mappers.fromPedidosDaoToPedidos
import mappers.fromTareaDaoToTarea
import models.Pedidos
import models.Tarea
import mu.KotlinLogging
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

import java.util.*

private val logger = KotlinLogging.logger {}

/**
 * Tareas repository impl
 *
 * @property tareasDao
 * @constructor Create empty Tareas repository impl
 */
class TareasRepositoryImpl(
    private val tareasDao: IntEntityClass<TareaDao>
) : TareasRepository {

    override fun findAll(): List<Tarea> = transaction {
        logger.debug { "findAll()" }
        tareasDao.all().map { it.fromTareaDaoToTarea() }
    }

    override fun findById(id: Int): Tarea? = transaction {
        logger.debug { "findAll()" }
        tareasDao.findById(id)?.fromTareaDaoToTarea()
    }

    override fun findbyUUID(uuid: UUID): Tarea? = transaction {
        logger.debug { "findById($uuid)" }
        val tareas = TareaDao.find { TareaTable.uuidTarea eq uuid }
        return@transaction tareas.first().fromTareaDaoToTarea()
    }

    override fun save(entity: Tarea): Tarea = transaction {
        val existe = tareasDao.findById(entity.id)
        existe?.let {
            update(entity, existe)
        } ?: run {
            insert(entity)
        }
    }

    override fun delete(entity: Tarea): Boolean = transaction {
        val existe = tareasDao.findById(entity.id) ?: return@transaction false
        // Si existe lo borramos
        logger.debug { "delete($entity) - borrando" }
        existe.delete()
        true
    }


    private fun insert(entity: Tarea): Tarea {
        logger.debug { "save($entity) - creando" }
        return tareasDao.new() {
            uuidTarea = entity.uuidTarea
            producto = ProductoDao.findById(entity.producto.id)?: throw ProductoException("El producto no existe con id: ${entity.producto.id}")
            precio = entity.precio
            descripcion = entity.descripcion
            empleado = UsuarioDao.findById(entity.empleado.id)?: throw UsuarioException("El empleado no existe con id: ${entity.empleado.id}")
            turno = TurnoDao.findById(entity.turno.id)?: throw TurnoException("El turno no existe con id: ${entity.turno.id}")
            estadoCompletado = entity.estadoCompletado
            maquinaEncordar = entity.maquinaEncordar?.let { MaquinaEncordarDao.findById(it.id) ?: throw MaquinaException("La Maquina no existe con id: ${entity.maquinaEncordar.id}") }
            maquinaPersonalizacion = entity.maquinaPersonalizacion?.let { MaquinaPersonalizacionDao.findById(it.id)?: throw MaquinaException("La Maquina no existe con id: ${entity.maquinaPersonalizacion.id}") }
            pedido = PedidosDao.findById(entity.pedido.id)?: throw PedidoException("El pedido no existe con id: ${entity.pedido.id}")
        }.fromTareaDaoToTarea()
    }

    private fun update(entity: Tarea, existe: TareaDao): Tarea {
        logger.debug { "save($entity) - actualizando" }
        // Si existe actualizamos
        return existe.apply {
            uuidTarea = entity.uuidTarea
            producto = ProductoDao.findById(entity.producto.id)?: throw ProductoException("El producto no existe con id: ${entity.producto.id}")
            precio = entity.precio
            descripcion = entity.descripcion
            empleado = UsuarioDao.findById(entity.empleado.id)?: throw UsuarioException("El empleado no existe con id: ${entity.empleado.id}")
            turno = TurnoDao.findById(entity.turno.id)?: throw TurnoException("El turno no existe con id: ${entity.turno.id}")
            estadoCompletado = entity.estadoCompletado
            maquinaEncordar = entity.maquinaEncordar?.let { MaquinaEncordarDao.findById(it.id)?: throw MaquinaException("La Maquina no existe con id: ${entity.maquinaEncordar.id}") }
            maquinaPersonalizacion = entity.maquinaPersonalizacion?.let { MaquinaPersonalizacionDao.findById(it.id) ?: throw MaquinaException("La Maquina no existe con id: ${entity.maquinaPersonalizacion.id}") }
            pedido = PedidosDao.findById(entity.pedido.id)?: throw PedidoException("El pedido no existe con id: ${entity.pedido.id}")
        }.fromTareaDaoToTarea()
    }


}