package repository.UsuarioRepository

import models.Turno
import models.Usuario
import models.enums.TipoPerfil
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertAll
import repositories.TurnosRepository.TurnosRepositoryImpl
import repositories.UsuarioRepository.UsuarioRepositoryImpl
import services.Password
import java.time.LocalDateTime
import java.util.*


@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class UsuarioRepositoryImplTest {

    val repositorio = UsuarioRepositoryImpl()
    val usuarioTest =  Usuario(
        1,
        UUID.fromString("492a7f86-c34d-43e3-ba77-8083a542f427"),
        "Mario",
        "Sánchez",
        "mario.sanchez@gmail.com",
        Password().encriptar("marioSanchez"),
        TipoPerfil.ENCORDADOR,
        TurnosRepositoryImpl().findById(1)
    )

    @Test
    @Order(1)
    fun findAll() {
        val test = repositorio.findAll()
        assertAll(
            { assertFalse(test.isEmpty()) },
            { assertEquals(test.first().uuid, usuarioTest.uuid) },
            { assertEquals(test.first().nombre, usuarioTest.nombre) },
            { assertEquals(test.first().apellido, usuarioTest.apellido) },
            { assertEquals(test.first().email, usuarioTest.email) },
            { assertEquals(1, test.size) }
        )

    }

    @Test
    @Order(2)
    fun findById() {
        val testId = repositorio.findById(usuarioTest.id)
        assertAll(
            { assertEquals(testId!!.uuid, usuarioTest.uuid) },
            { assertEquals(testId!!.nombre, usuarioTest.nombre) },
            { assertEquals(testId!!.apellido, usuarioTest.apellido) },
            { assertEquals(testId!!.email, usuarioTest.email) },
        )
    }

    @Test
    @Order(3)
    fun findbyUUID() {
        val testUUId = repositorio.findbyUUID(usuarioTest.uuid)
        assertAll(
            { assertEquals(testUUId!!.uuid, usuarioTest.uuid) },
            { assertEquals(testUUId!!.nombre, usuarioTest.nombre) },
            { assertEquals(testUUId!!.apellido, usuarioTest.apellido) },
            { assertEquals(testUUId!!.email, usuarioTest.email) },
        )
    }

    @Test
    @Order(4)
    fun save() {
        val testSave = repositorio.save(usuarioTest)
        assertAll(
            { assertEquals(testSave.uuid, usuarioTest.uuid) },
            { assertEquals(testSave.nombre, usuarioTest.nombre) },
            { assertEquals(testSave.apellido, usuarioTest.apellido) },
            { assertEquals(testSave.email, usuarioTest.email) },
        )
    }

  @Test
  @Order(5)
    fun delete() {
        val testDelete = repositorio.delete(usuarioTest)
        assertAll(
            { assertTrue(testDelete) },
        )
    }



    companion object {
        @JvmStatic
        @BeforeAll
        fun setUp(): Unit {
            val turno =  Turno(
                0,
                UUID.fromString("492a7f86-c34d-4313-ba77-8083a542f425"),
                LocalDateTime.of(2022, 12, 5, 8, 0),
                LocalDateTime.of(2022, 12, 5, 10, 0)
            )
            TurnosRepositoryImpl().save(turno)
            val usuarioTest =  Usuario(
                0,
                UUID.fromString("492a7f86-c34d-43e3-ba77-8083a542f427"),
                "Mario",
                "Sánchez",
                "mario.sanchez@gmail.com",
                Password().encriptar("marioSanchez"),
                TipoPerfil.ENCORDADOR,
                TurnosRepositoryImpl().findById(1)
            )
            val usuario = UsuarioRepositoryImpl().save(usuarioTest)
        }
    }
}