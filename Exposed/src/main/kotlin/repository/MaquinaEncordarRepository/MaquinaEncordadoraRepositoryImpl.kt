package repository.MaquinaEncordarRepository

import entities.MaquinaEncordarDao
import entities.MaquinaEncordarTable
import entities.MaquinaPersonalizacionDao
import entities.MaquinaPersonalizacionTable
import mappers.fromMaquinaEncordadoraDaoToMaquinaEncordar
import mappers.fromMaquinaPersonalizacionDaoToMaquinaPersonalizacion
import models.Maquina
import mu.KotlinLogging
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.transactions.transaction
import repository.MaquinaPersonalizacionRepository.MaquinaPersonalizacionRepository
import java.util.*

private val logger = KotlinLogging.logger {}

/**
 * Maquina encordadora repository impl
 *
 * @property maquinaEncordaDao
 * @constructor Create empty Maquina encordadora repository impl
 */
class MaquinaEncordadoraRepositoryImpl(private val maquinaEncordaDao: IntEntityClass<MaquinaEncordarDao>) :
    MaquinaEncordarRepository {


    override fun findAll(): List<Maquina.MaquinaEncordadora> = transaction {
        logger.debug { "findAll()" }
        maquinaEncordaDao.all().map { it.fromMaquinaEncordadoraDaoToMaquinaEncordar() }
    }


    override fun findById(id: Int): Maquina.MaquinaEncordadora? = transaction {
        logger.debug { "findById($id)" }
        maquinaEncordaDao.findById(id)?.fromMaquinaEncordadoraDaoToMaquinaEncordar()
    }

    override fun save(entity: Maquina.MaquinaEncordadora): Maquina.MaquinaEncordadora = transaction {

        val existe = maquinaEncordaDao.findById(entity.id)
        existe?.let {
            update(entity, existe)
        } ?: run {
            insert(entity)
        }
    }

    override fun delete(entity: Maquina.MaquinaEncordadora): Boolean = transaction {
        val existe = maquinaEncordaDao.findById(entity.id) ?: return@transaction false
        // Si existe lo borramos
        logger.debug { "delete($entity) - borrando" }
        existe.delete()
        true
    }

    override fun findbyUUID(uuid: UUID): Maquina.MaquinaEncordadora? = transaction {
        logger.debug { "findById($uuid)" }
        val encordadoras = MaquinaEncordarDao.find { MaquinaEncordarTable.numSerie eq uuid }
        return@transaction encordadoras.first().fromMaquinaEncordadoraDaoToMaquinaEncordar()
    }

    private fun insert(entity: Maquina.MaquinaEncordadora): Maquina.MaquinaEncordadora {
        logger.debug { "save($entity) - creando" }
        return maquinaEncordaDao.new {
            numSerie = entity.numSerie
            marca = entity.marca
            modelo = entity.modelo
            fechaAdquisicion = entity.fechaAdquisicion
            automatico = entity.automatico
            tensionMaxima = entity.tensionMaxima
            tensionMinima = entity.tensionMinima

        }.fromMaquinaEncordadoraDaoToMaquinaEncordar()
    }

    private fun update(entity: Maquina.MaquinaEncordadora, existe: MaquinaEncordarDao): Maquina.MaquinaEncordadora {
        logger.debug { "save($entity) - actualizando" }
        // Si existe actualizamos
        return existe.apply {
            //   numSerie=entity.numSerie
            marca = entity.marca
            modelo = entity.modelo
            fechaAdquisicion = entity.fechaAdquisicion
            automatico = entity.automatico
            tensionMaxima = entity.tensionMaxima
            tensionMinima = entity.tensionMinima
        }.fromMaquinaEncordadoraDaoToMaquinaEncordar()
    }


}