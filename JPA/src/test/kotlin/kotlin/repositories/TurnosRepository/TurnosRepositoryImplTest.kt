package repository.TurnosRepository


import models.Turno

import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertAll
import repositories.TurnosRepository.TurnosRepositoryImpl

import java.time.LocalDateTime
import java.util.*


@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TurnosRepositoryImplTest {

    val repositorio = TurnosRepositoryImpl()
    val turnoTest =
        Turno(
            1,
            UUID.fromString("492a7f86-c34d-43e3-ba77-8083a542f426"),
            LocalDateTime.of(2022, 12, 5, 8, 0),
            LocalDateTime.of(2022, 12, 5, 10, 0)
        )

    @Order(1)
    @Test
    fun findAll() {
        val test = repositorio.findAll()
        assertAll(
            { assertFalse(test.isEmpty()) },
            { assertEquals(test.first().uuidTurno, turnoTest.uuidTurno) },
            { assertEquals(test.first().fechaInicio, turnoTest.fechaInicio) },
            { assertEquals(test.first().fechaFin, turnoTest.fechaFin) },
            { assertEquals(test.size, 1) }
        )
    }

    @Order(2)
    @Test
    fun findById() {
        val testId = repositorio.findById(turnoTest.id)
        assertAll(
            { assertEquals(testId!!.uuidTurno, turnoTest.uuidTurno) },
            { assertEquals(testId!!.fechaInicio, turnoTest.fechaInicio) },
            { assertEquals(testId!!.fechaFin, turnoTest.fechaFin) }
        )
    }

    @Order(3)
    @Test
    fun findbyUUID() {
        val testUUId = repositorio.findbyUUID(turnoTest.uuidTurno)
        assertAll(
            { assertEquals(testUUId!!.uuidTurno, turnoTest.uuidTurno) },
            { assertEquals(testUUId!!.fechaInicio, turnoTest.fechaInicio) },
            { assertEquals(testUUId!!.fechaFin, turnoTest.fechaFin) }
        )
    }

    @Order(4)
    @Test
    fun save() {
        val testSave = repositorio.save(turnoTest)
        assertAll(
            { assertEquals(testSave.uuidTurno, turnoTest.uuidTurno) },
            { assertEquals(testSave.fechaInicio, turnoTest.fechaInicio) },
            { assertEquals(testSave.fechaFin, turnoTest.fechaFin) }
        )
    }

    @Order(5)
    @Test
    fun delete() {
        val testDelete = repositorio.delete(turnoTest)
        assertAll(
            { assertTrue(testDelete) }
        )
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun setUp(): Unit {
            val turnoTest =
                Turno(
                    0,
                    UUID.fromString("492a7f86-c34d-43e3-ba77-8083a542f426"),
                    LocalDateTime.of(2022, 12, 5, 8, 0),
                    LocalDateTime.of(2022, 12, 5, 10, 0)
                )

            val usuario = TurnosRepositoryImpl().save(turnoTest)
        }
    }
}