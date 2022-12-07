package repository.MaquinaEncordarRepository

import config.AppConfig
import db.DataBaseManager
import entities.MaquinaEncordarDao
import entities.MaquinaPersonalizacionDao
import entities.ProductoDao
import models.Maquina
import models.Producto
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import repository.MaquinaPersonalizacionRepository.MaquinaPersonalizacionRepositoryImpl
import repository.ProductosRepository.ProductosRepositoryImpl
import java.time.LocalDate
import java.util.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class MaquinaEncordadoraRepositoryImplTest {

    val repositorio = MaquinaEncordadoraRepositoryImpl(MaquinaEncordarDao)
    val maquinaTest = Maquina.MaquinaEncordadora(
        1,
        UUID.fromString("492a7f86-c34d-4313-ba77-8083a542f425"),
        "nadal",
        "rojo",
        LocalDate.now(),
        true,
        20.0,
        12.4
    )

    @Order(1)
    @Test
    fun findAll() {
        val test = repositorio.findAll()
        Assertions.assertAll(
            { assertFalse(test.isEmpty()) },
            { assertEquals(test.first().numSerie, maquinaTest.numSerie) },
            { assertEquals(test.first().marca, maquinaTest.marca) },
            { assertEquals(test.first().modelo, maquinaTest.modelo) },
            { assertEquals(test.first().automatico, maquinaTest.automatico) },
            { assertEquals(test.first().fechaAdquisicion, maquinaTest.fechaAdquisicion) },
            { assertEquals(test.first().tensionMaxima, maquinaTest.tensionMaxima) },
            { assertEquals(test.first().tensionMinima, maquinaTest.tensionMinima) },
            { assertEquals(test.size, 1) }
        )
    }

    @Order(2)
    @Test
    fun findById() {
        val testID = repositorio.findById(maquinaTest.id)
        Assertions.assertAll(
            { assertEquals(testID!!.numSerie, maquinaTest.numSerie) },
            { assertEquals(testID!!.marca, maquinaTest.marca) },
            { assertEquals(testID!!.modelo, maquinaTest.modelo) },
            { assertEquals(testID!!.automatico, maquinaTest.automatico) },
            { assertEquals(testID!!.fechaAdquisicion, maquinaTest.fechaAdquisicion) },
            { assertEquals(testID!!.tensionMaxima, maquinaTest.tensionMaxima) },
            { assertEquals(testID!!.tensionMinima, maquinaTest.tensionMinima) }
        )
    }

    @Order(3)
    @Test
    fun findbyUUID() {
        val testUUID = repositorio.findbyUUID(maquinaTest.numSerie)
        Assertions.assertAll(
            { assertEquals(testUUID!!.numSerie, maquinaTest.numSerie) },
            { assertEquals(testUUID!!.marca, maquinaTest.marca) },
            { assertEquals(testUUID!!.modelo, maquinaTest.modelo) },
            { assertEquals(testUUID!!.automatico, maquinaTest.automatico) },
            { assertEquals(testUUID!!.fechaAdquisicion, maquinaTest.fechaAdquisicion) },
            { assertEquals(testUUID!!.tensionMaxima, maquinaTest.tensionMaxima) },
            { assertEquals(testUUID!!.tensionMinima, maquinaTest.tensionMinima) }
        )
    }

    @Order(4)
    @Test
    fun save() {
        val testSave = repositorio.save(maquinaTest)
        Assertions.assertAll(
            { assertEquals(testSave.numSerie, maquinaTest.numSerie) },
            { assertEquals(testSave.marca, maquinaTest.marca) },
            { assertEquals(testSave.modelo, maquinaTest.modelo) },
            { assertEquals(testSave.automatico, maquinaTest.automatico) },
            { assertEquals(testSave.fechaAdquisicion, maquinaTest.fechaAdquisicion) },
            { assertEquals(testSave.tensionMaxima, maquinaTest.tensionMaxima) },
            { assertEquals(testSave.tensionMinima, maquinaTest.tensionMinima) }
        )
    }

    @Order(5)
    @Test
    fun delete() {
        val testDelete = repositorio.delete(maquinaTest)
        Assertions.assertAll(
            { assertTrue(testDelete) },
        )
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun setUp(): Unit {
            val maquinaTest =  Maquina.MaquinaEncordadora(
                0,
                UUID.fromString("492a7f86-c34d-4313-ba77-8083a542f425"),
                "nadal",
                "rojo",
                LocalDate.now(),
                true,
                20.0,
                12.4
            )
            DataBaseManager.init(AppConfig.DEFAULT)
            val producto = MaquinaEncordadoraRepositoryImpl(MaquinaEncordarDao).save(maquinaTest)
        }
    }


}