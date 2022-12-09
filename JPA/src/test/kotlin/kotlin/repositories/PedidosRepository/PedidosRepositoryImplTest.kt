package repository.PedidosRepository


import models.Pedidos

import models.Usuario
import models.enums.TipoEstado
import models.enums.TipoPerfil
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertAll
import repositories.PedidosRepository.PedidosRepositoryImpl
import repositories.UsuarioRepository.UsuarioRepositoryImpl
import services.Password
import java.time.LocalDate
import java.util.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class PedidosRepositoryImplTest {

    val repositorio = PedidosRepositoryImpl()
    val pedidoTest = Pedidos(
    1,
        UUID.fromString("492a7f86-c34d-43e3-ba77-8083a542f425"),
        TipoEstado.RECIBIDO,
        LocalDate.now(),
        LocalDate.of(2022, 12, 12),
        LocalDate.of(2022, 12, 13),
    120.5,
        Usuario(
            1,
            UUID.fromString("492a7f86-c34d-43e3-ba77-8083a542f427"),
            "Mario",
            "Sánchez",
            "mario.sanchez@gmail.com",
            Password().encriptar("marioSanchez"),
            TipoPerfil.USUARIO,
            null
        )
    )

    @Order(1)
    @Test
    fun findAll() {
        val test = repositorio.findAll()
        assertAll(
            { assertFalse(test.isEmpty()) },
            { assertEquals(test.first().uuid, pedidoTest.uuid) },
            { assertEquals(test.first().estado, pedidoTest.estado) },
            { assertEquals(test.first().usuario, pedidoTest.usuario) },
            { assertEquals(test.first().fechaEntrada, pedidoTest.fechaEntrada) },
            { assertEquals(test.first().fechaEntrega, pedidoTest.fechaEntrega) },
            { assertEquals(test.first().fechaSalidaProgramada, pedidoTest.fechaSalidaProgramada) },
            { assertEquals(test.size, 1) }
        )
    }

    @Order(2)
    @Test
    fun findById() {
        val test = repositorio.findById(pedidoTest.id)
        assertAll(
            { assertEquals(test!!.uuid, pedidoTest.uuid) },
            { assertEquals(test!!.estado, pedidoTest.estado) },
            { assertEquals(test!!.usuario, pedidoTest.usuario) },
            { assertEquals(test!!.fechaEntrada, pedidoTest.fechaEntrada) },
            { assertEquals(test!!.fechaEntrega, pedidoTest.fechaEntrega) },
            { assertEquals(test!!.fechaSalidaProgramada, pedidoTest.fechaSalidaProgramada) },
        )
    }

    @Order(3)
    @Test
    fun findbyUUID() {
        val test = repositorio.findbyUUID(pedidoTest.uuid)
        assertAll(
            { assertEquals(test!!.uuid, pedidoTest.uuid) },
            { assertEquals(test!!.estado, pedidoTest.estado) },
            { assertEquals(test!!.usuario, pedidoTest.usuario) },
            { assertEquals(test!!.fechaEntrada, pedidoTest.fechaEntrada) },
            { assertEquals(test!!.fechaEntrega, pedidoTest.fechaEntrega) },
            { assertEquals(test!!.fechaSalidaProgramada, pedidoTest.fechaSalidaProgramada) },
        )
    }

    @Order(4)
    @Test
    fun save() {
        val test = repositorio.save(pedidoTest)
        assertAll(
            { assertEquals(test.uuid, pedidoTest.uuid) },
            { assertEquals(test.estado, pedidoTest.estado) },
            { assertEquals(test.usuario, pedidoTest.usuario) },
            { assertEquals(test.fechaEntrada, pedidoTest.fechaEntrada) },
            { assertEquals(test.fechaEntrega, pedidoTest.fechaEntrega) },
            { assertEquals(test.fechaSalidaProgramada, pedidoTest.fechaSalidaProgramada) },
        )
    }

    @Order(5)
    @Test
    fun delete() {
        val testDelete = repositorio.delete(pedidoTest)
        assertAll(
            { assertTrue(testDelete) },
        )
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun setUp(): Unit {

            val usuarioTest =  Usuario(
                0,
                UUID.fromString("492a7f86-c34d-43e3-ba77-8083a542f427"),
                "Mario",
                "Sánchez",
                "mario.sanchez@gmail.com",
                Password().encriptar("marioSanchez"),
                TipoPerfil.USUARIO,
                null
            )
            val usuario = UsuarioRepositoryImpl().save(usuarioTest)
            val pedidoTest = Pedidos(
                0,
                UUID.fromString("492a7f86-c34d-43e3-ba77-8083a542f425"),
                TipoEstado.RECIBIDO,
                LocalDate.now(),
                LocalDate.of(2022, 12, 12),
                LocalDate.of(2022, 12, 13),
                120.5,
                UsuarioRepositoryImpl().findById(1)!!
            )

            val pedido = PedidosRepositoryImpl().save(pedidoTest)
        }
    }
}