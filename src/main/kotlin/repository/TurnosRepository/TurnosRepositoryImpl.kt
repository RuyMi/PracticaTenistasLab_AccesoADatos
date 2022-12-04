package repository.TurnosRepository

import entities.TurnoDao
import entities.TurnoTable
import entities.UsuarioDao
import mappers.fromTurnoDaoToTurno
import mappers.fromUsuarioDaoToUsuario
import models.Turno
import models.Usuario
import mu.KotlinLogging
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
private val logger = KotlinLogging.logger {}


class TurnosRepositoryImpl(private val turnoDao: IntEntityClass<TurnoDao>):TurnosRepository {

    override fun findAll(): List<Turno> = transaction {
        logger.debug { "findAll()" }
        turnoDao.all().map { it.fromTurnoDaoToTurno() }
    }

    override fun findById(id: Int): Turno? = transaction {
        logger.debug { "findAll()" }
        turnoDao.findById(id)?.fromTurnoDaoToTurno()
    }

    override fun findbyUUID(uuid: UUID): Turno? = transaction {
        logger.debug { "findById($uuid)" }
        val turno = TurnoDao.find { TurnoTable.uuidTurno eq uuid }
        return@transaction turno.first().fromTurnoDaoToTurno()
    }

    override fun save(entity: Turno): Turno = transaction {
        val existe = turnoDao.findById(entity.id)
        existe?.let {
            update(entity, existe)
        } ?: run {
            insert(entity)
        }
    }

    override fun delete(entity: Turno): Boolean = transaction {
        val existe = turnoDao.findById(entity.id) ?: return@transaction false
        // Si existe lo borramos
        logger.debug { "delete($entity) - borrando" }
        existe.delete()
        true
    }

    private fun insert(entity: Turno): Turno {
        repository.TurnosRepository.logger.debug { "save($entity) - creando" }
        return turnoDao.new() {
            uuidTurno = entity.uuidTurno
            fechaInicio=entity.fechaInicio
            fechaFin=entity.fechaFin
        }.fromTurnoDaoToTurno()
    }

    private fun update(entity: Turno, existe: TurnoDao): Turno {
        repository.TurnosRepository.logger.debug { "save($entity) - actualizando" }
        // Si existe actualizamos
        return existe.apply {
            uuidTurno = entity.uuidTurno
            fechaInicio=entity.fechaInicio
            fechaFin=entity.fechaFin
        }.fromTurnoDaoToTurno()
    }
}