package repository.ProductosRepository

import config.AppConfig
import db.DataBaseManager
import entities.ProductoDao
import entities.UsuarioDao
import models.Producto
import models.Turno
import models.Usuario
import models.enums.TipoPerfil
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertAll
import repository.UsuarioRepository.UsuarioRepositoryImpl
import services.Password
import java.time.LocalDateTime
import java.util.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ProductosRepositoryImplTest {

    val repositorio = ProductosRepositoryImpl(ProductoDao)
    val profuctoTest = Producto(
        1,
        UUID.fromString("492a7f86-c34d-43e3-ba77-8083a542f427"),
        "Wilson",
        "raqueta",
        20.2,
        12
    )

    @Order(1)
    @Test
    fun findAll() {
        val test = repositorio.findAll()
        assertAll(
            { assertFalse(test.isEmpty()) },
            { assertEquals(test.first().uuid, profuctoTest.uuid) },
            { assertEquals(test.first().marca, profuctoTest.marca) },
            { assertEquals(test.first().modelo, profuctoTest.modelo) },
            { assertEquals(test.first().precio, profuctoTest.precio) },
            { assertEquals(test.first().stock, profuctoTest.stock) },
            { assertEquals(test.size, 1) }
        )
    }

    @Order(2)
    @Test
    fun findById() {
        val testID = repositorio.findById(profuctoTest.id)
        assertAll(
            { assertEquals(testID!!.uuid, profuctoTest.uuid) },
            { assertEquals(testID!!.marca, profuctoTest.marca) },
            { assertEquals(testID!!.modelo, profuctoTest.modelo) },
            { assertEquals(testID!!.precio, profuctoTest.precio) },
            { assertEquals(testID!!.stock, profuctoTest.stock) },
        )
    }

    @Order(3)
    @Test
    fun findbyUUID() {
        val testUUID = repositorio.findbyUUID(profuctoTest.uuid)
        assertAll(
            { assertEquals(testUUID!!.uuid, profuctoTest.uuid) },
            { assertEquals(testUUID!!.marca, profuctoTest.marca) },
            { assertEquals(testUUID!!.modelo, profuctoTest.modelo) },
            { assertEquals(testUUID!!.precio, profuctoTest.precio) },
            { assertEquals(testUUID!!.stock, profuctoTest.stock) },
        )
    }

    @Order(4)
    @Test
    fun save() {
        val testSave = repositorio.save(profuctoTest)
        assertAll(
            { assertEquals(testSave.uuid, profuctoTest.uuid) },
            { assertEquals(testSave.marca, profuctoTest.marca) },
            { assertEquals(testSave.modelo, profuctoTest.modelo) },
            { assertEquals(testSave.precio, profuctoTest.precio) },
            { assertEquals(testSave.stock, profuctoTest.stock) },
        )
    }

    @Order(5)
    @Test
    fun delete() {
        val testDelete = repositorio.delete(profuctoTest)
        assertAll(
            { assertTrue(testDelete) },
        )
    }
    companion object {
        @JvmStatic
        @BeforeAll
        fun setUp(): Unit {
            val profuctoTest = Producto(
                0,
                UUID.fromString("492a7f86-c34d-43e3-ba77-8083a542f427"),
                "Wilson",
                "raqueta",
                20.2,
                12
            )
            DataBaseManager.init(AppConfig.DEFAULT)
            val producto = ProductosRepositoryImpl(ProductoDao).save(profuctoTest)
        }
    }
}