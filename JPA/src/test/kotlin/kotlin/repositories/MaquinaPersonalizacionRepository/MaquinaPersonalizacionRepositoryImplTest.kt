package repository.MaquinaPersonalizacionRepository


import models.MaquinaPersonalizacion
import models.Producto
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertAll
import repositories.MaquinaPersonalizacionRepository.MaquinaPersonalizacionRepositoryImpl

import java.time.LocalDate
import java.util.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class MaquinaPersonalizacionRepositoryImplTest {

    val repositorio = MaquinaPersonalizacionRepositoryImpl()
    val maquinaTest = MaquinaPersonalizacion(
        1,
        UUID.fromString("492a7f86-c34d-43e3-ba77-8083a542f427"),
        "perso",
        "rojo",
        LocalDate.now(),
        true,
        1.0,
        10.4
    )

    @Order(1)
    @Test
    fun findAll() {
        val test = repositorio.findAll()
        assertAll(
            { assertFalse(test.isEmpty()) },
            { assertEquals(test.first().numSerie, maquinaTest.numSerie) },
            { assertEquals(test.first().marca, maquinaTest.marca) },
            { assertEquals(test.first().modelo, maquinaTest.modelo) },
            { assertEquals(test.first().balance, maquinaTest.balance) },
            { assertEquals(test.first().fechaAdquisicion, maquinaTest.fechaAdquisicion) },
            { assertEquals(test.first().rigidez, maquinaTest.rigidez) },
            { assertEquals(test.first().swingweight, maquinaTest.swingweight)},
            { assertEquals(test.size, 1) }
        )
    }

    @Order(2)
    @Test
    fun findById() {
        val testID = repositorio.findById(maquinaTest.id)
        assertAll(
            { assertEquals(testID!!.numSerie, maquinaTest.numSerie) },
            { assertEquals(testID!!.marca, maquinaTest.marca) },
            { assertEquals(testID!!.modelo, maquinaTest.modelo) },
            { assertEquals(testID!!.balance, maquinaTest.balance) },
            { assertEquals(testID!!.fechaAdquisicion, maquinaTest.fechaAdquisicion) },
            { assertEquals(testID!!.rigidez, maquinaTest.rigidez) },
            { assertEquals(testID!!.swingweight, maquinaTest.swingweight)}
        )
    }

    @Order(3)
    @Test
    fun findbyUUID() {
        val testID = repositorio.findbyUUID(maquinaTest.numSerie)
        assertAll(
            { assertEquals(testID!!.numSerie, maquinaTest.numSerie) },
            { assertEquals(testID!!.marca, maquinaTest.marca) },
            { assertEquals(testID!!.modelo, maquinaTest.modelo) },
            { assertEquals(testID!!.balance, maquinaTest.balance) },
            { assertEquals(testID!!.fechaAdquisicion, maquinaTest.fechaAdquisicion) },
            { assertEquals(testID!!.rigidez, maquinaTest.rigidez) },
            { assertEquals(testID!!.swingweight, maquinaTest.swingweight)}
        )
    }

    @Order(4)
    @Test
    fun save() {
        val testSave = repositorio.save(maquinaTest)
        assertAll(
            { assertEquals(testSave.numSerie, maquinaTest.numSerie) },
            { assertEquals(testSave.marca, maquinaTest.marca) },
            { assertEquals(testSave.modelo, maquinaTest.modelo) },
            { assertEquals(testSave.balance, maquinaTest.balance) },
            { assertEquals(testSave.fechaAdquisicion, maquinaTest.fechaAdquisicion) },
            { assertEquals(testSave.rigidez, maquinaTest.rigidez) },
            { assertEquals(testSave.swingweight, maquinaTest.swingweight)}
        )
    }

    @Order(5)
    @Test
    fun delete() {
        val testDelete = repositorio.delete(maquinaTest)
        assertAll(
            { assertTrue(testDelete) },
        )
    }



    companion object {
        @JvmStatic
        @BeforeAll
        fun setUp(): Unit {
            val maquinaTest = MaquinaPersonalizacion(
                0,
                UUID.fromString("492a7f86-c34d-43e3-ba77-8083a542f427"),
                "perso",
                "rojo",
                LocalDate.now(),
                true,
                1.0,
                10.4
            )

            val maquina = MaquinaPersonalizacionRepositoryImpl().save(maquinaTest)
        }
    }
}