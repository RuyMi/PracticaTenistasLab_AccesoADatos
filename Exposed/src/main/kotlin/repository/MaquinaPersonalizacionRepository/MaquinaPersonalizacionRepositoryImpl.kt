package repository.MaquinaPersonalizacionRepository

import entities.MaquinaPersonalizacionDao
import entities.MaquinaPersonalizacionTable
import mappers.fromMaquinaPersonalizacionDaoToMaquinaPersonalizacion
import models.Maquina
import mu.KotlinLogging
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

private val logger = KotlinLogging.logger {}

/**
 * Maquina personalizacion repository impl
 *
 * @property maquinaPerDao
 * @constructor Create empty Maquina personalizacion repository impl
 */
class MaquinaPersonalizacionRepositoryImpl(private val maquinaPerDao: IntEntityClass<MaquinaPersonalizacionDao>) :
    MaquinaPersonalizacionRepository {


    override fun findAll(): List<Maquina.MaquinaPersonalizacion> = transaction {
        logger.debug { "findAll()" }
        maquinaPerDao.all().map { it.fromMaquinaPersonalizacionDaoToMaquinaPersonalizacion() }
    }



    override fun findById(id: Int): Maquina.MaquinaPersonalizacion? = transaction {
        logger.debug { "findById($id)" }
        maquinaPerDao.findById(id)?.fromMaquinaPersonalizacionDaoToMaquinaPersonalizacion()
    }

    override fun save(entity: Maquina.MaquinaPersonalizacion): Maquina.MaquinaPersonalizacion = transaction{

        val existe = maquinaPerDao.findById(entity.id)
        existe?.let {
            update(entity, existe)
        } ?: run {
            insert(entity)
        }
    }

    override fun delete(entity: Maquina.MaquinaPersonalizacion): Boolean= transaction {
        val existe = maquinaPerDao.findById(entity.id) ?: return@transaction false
        // Si existe lo borramos
        logger.debug { "delete($entity) - borrando" }
        existe.delete()
        true
    }

   override fun findbyUUID(uuid: UUID): Maquina.MaquinaPersonalizacion? = transaction {
        logger.debug { "findById($uuid)" }
        val encordadoras = MaquinaPersonalizacionDao.find { MaquinaPersonalizacionTable.numSerie eq uuid }
        return@transaction encordadoras.first().fromMaquinaPersonalizacionDaoToMaquinaPersonalizacion()
    }

    private fun insert(entity: Maquina.MaquinaPersonalizacion): Maquina.MaquinaPersonalizacion {
        logger.debug { "save($entity) - creando" }
        return maquinaPerDao.new {
           numSerie=entity.numSerie
            marca = entity.marca
            modelo = entity.modelo
            fechaAdquisicion = entity.fechaAdquisicion
            swingweight = entity.swingweight
            balance = entity.balance
            rigidez = entity.rigidez

        }.fromMaquinaPersonalizacionDaoToMaquinaPersonalizacion()
    }

    private fun update(entity:  Maquina.MaquinaPersonalizacion, existe: MaquinaPersonalizacionDao):  Maquina.MaquinaPersonalizacion {
        logger.debug { "save($entity) - actualizando" }
        // Si existe actualizamos
        return existe.apply {
         //   numSerie=entity.numSerie
            marca = entity.marca
            modelo = entity.modelo
            fechaAdquisicion = entity.fechaAdquisicion
            swingweight = entity.swingweight
            balance = entity.balance
            rigidez = entity.rigidez
        }.fromMaquinaPersonalizacionDaoToMaquinaPersonalizacion()
    }


}


