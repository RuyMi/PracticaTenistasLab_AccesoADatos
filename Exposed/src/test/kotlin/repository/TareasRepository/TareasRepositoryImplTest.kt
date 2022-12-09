package repository.TareasRepository

import config.AppConfig
import db.DataBaseManager
import entities.*
import models.*
import models.enums.TipoEstado
import models.enums.TipoPerfil
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import repository.MaquinaEncordarRepository.MaquinaEncordadoraRepositoryImpl
import repository.PedidosRepository.PedidosRepositoryImpl
import repository.ProductosRepository.ProductosRepositoryImpl
import repository.TurnosRepository.TurnosRepositoryImpl
import repository.UsuarioRepository.UsuarioRepositoryImpl
import services.Password
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TareasRepositoryImplTest {

    val repositorio = TareasRepositoryImpl(TareaDao)
    val tareaTest = Tarea(
        1,
        UUID.fromString("192a7f86-c34d-43e3-ba77-8083a542f425"),
        ProductosRepositoryImpl(ProductoDao).findById(1)!!,
        20.0,
        "Personalizacion",
        UsuarioRepositoryImpl(UsuarioDao).findById(1)!!,
        TurnosRepositoryImpl(TurnoDao).findById(1)!!,
        true,
        MaquinaEncordadoraRepositoryImpl(MaquinaEncordarDao).findById(1)!!,
        null,
        PedidosRepositoryImpl(PedidosDao, TareaDao).findById(1)!!
    )


    @Order(1)
    @Test
    fun findAll() {
        val test = repositorio.findAll()
        Assertions.assertAll(
            { assertFalse(test.isEmpty()) },
            { assertEquals(test.first().uuidTarea, tareaTest.uuidTarea) },
            { assertEquals(test.first().producto, tareaTest.producto) },
            { assertEquals(test.first().precio, tareaTest.precio) },
            { assertEquals(test.first().descripcion, tareaTest.descripcion) },
            { assertEquals(test.first().empleado, tareaTest.empleado) },
            { assertEquals(test.first().turno, tareaTest.turno) },
            { assertEquals(test.first().estadoCompletado, tareaTest.estadoCompletado) },
            { assertEquals(test.first().maquinaEncordar, tareaTest.maquinaEncordar) },
            { assertEquals(test.first().maquinaPersonalizacion, tareaTest.maquinaPersonalizacion) },
            { assertEquals(test.first().pedido, tareaTest.pedido) },
            { assertEquals(test.size, 1) }
        )
    }

    @Order(2)
    @Test
    fun findById() {
        val testID = repositorio.findById(tareaTest.id)
        Assertions.assertAll(
            { assertEquals(testID!!.uuidTarea, tareaTest.uuidTarea) },
            { assertEquals(testID!!.producto, tareaTest.producto) },
            { assertEquals(testID!!.precio, tareaTest.precio) },
            { assertEquals(testID!!.descripcion, tareaTest.descripcion) },
            { assertEquals(testID!!.empleado, tareaTest.empleado) },
            { assertEquals(testID!!.turno, tareaTest.turno) },
            { assertEquals(testID!!.estadoCompletado, tareaTest.estadoCompletado) },
            { assertEquals(testID!!.maquinaEncordar, tareaTest.maquinaEncordar) },
            { assertEquals(testID!!.maquinaPersonalizacion, tareaTest.maquinaPersonalizacion) },
            { assertEquals(testID!!.pedido, tareaTest.pedido) },
        )
    }

    @Order(3)
    @Test
    fun findbyUUID() {
        val testUUID = repositorio.findbyUUID(tareaTest.uuidTarea)
        Assertions.assertAll(
            { assertEquals(testUUID!!.uuidTarea, tareaTest.uuidTarea) },
            { assertEquals(testUUID!!.producto, tareaTest.producto) },
            { assertEquals(testUUID!!.precio, tareaTest.precio) },
            { assertEquals(testUUID!!.descripcion, tareaTest.descripcion) },
            { assertEquals(testUUID!!.empleado, tareaTest.empleado) },
            { assertEquals(testUUID!!.turno, tareaTest.turno) },
            { assertEquals(testUUID!!.estadoCompletado, tareaTest.estadoCompletado) },
            { assertEquals(testUUID!!.maquinaEncordar, tareaTest.maquinaEncordar) },
            { assertEquals(testUUID!!.maquinaPersonalizacion, tareaTest.maquinaPersonalizacion) },
            { assertEquals(testUUID!!.pedido, tareaTest.pedido) },
        )
    }

    @Order(4)
    @Test
    fun save() {
        val testSave = repositorio.save(tareaTest)
        Assertions.assertAll(
            { assertEquals(testSave.uuidTarea, tareaTest.uuidTarea) },
            { assertEquals(testSave.producto, tareaTest.producto) },
            { assertEquals(testSave.precio, tareaTest.precio) },
            { assertEquals(testSave.descripcion, tareaTest.descripcion) },
            { assertEquals(testSave.empleado, tareaTest.empleado) },
            { assertEquals(testSave.turno, tareaTest.turno) },
            { assertEquals(testSave.estadoCompletado, tareaTest.estadoCompletado) },
            { assertEquals(testSave.maquinaEncordar, tareaTest.maquinaEncordar) },
            { assertEquals(testSave.maquinaPersonalizacion, tareaTest.maquinaPersonalizacion) },
            { assertEquals(testSave.pedido, tareaTest.pedido) },
        )
    }

    @Order(5)
    @Test
    fun delete() {
        val testDelete = repositorio.delete(tareaTest)
        Assertions.assertAll(
            { assertTrue(testDelete) }
        )
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun setUp(): Unit {
            DataBaseManager.init(AppConfig.TEST1)
            val turno =  Turno(
                0,
                UUID.fromString("492a7f86-c34d-4313-ba77-8083a542f425"),
                LocalDateTime.of(2022, 12, 5, 8, 0),
                LocalDateTime.of(2022, 12, 5, 10, 0)
            )
            TurnosRepositoryImpl(TurnoDao).save(turno)
            val usuarioTest = Usuario(
                    0,
                    UUID.fromString("492a7f86-c32d-43e3-ba77-8083a542f425"),
                    "Mario",
                    "Sánchez",
                    "mario.sanchez@gmail.com",
                    Password().encriptar("marioSanchez"),
                    TipoPerfil.ENCORDADOR,
                    TurnosRepositoryImpl(TurnoDao).findById(1)
            )
            val usuario = UsuarioRepositoryImpl(UsuarioDao).save(usuarioTest)
            val maquina = Maquina.MaquinaEncordadora(
                0,
                UUID.fromString("492a7f86-d34d-43e3-ba77-8083a542f425"),
                "nadal",
                "rojo",
                LocalDate.now(),
                true,
                20.0,
                12.4
            )
            MaquinaEncordadoraRepositoryImpl(MaquinaEncordarDao).save(maquina)
            val producto = Producto(
                0,
                UUID.fromString("492a7f86-c34d-43e3-ba77-8183a542f425"),
                "Wilson",
                "raqueta",
                20.2,
                12
            )
            ProductosRepositoryImpl(ProductoDao).save(producto)

            val pedidoTest = Pedidos(
                0,
                UUID.fromString("492a7f86-c34d-43e3-ba77-8083a542f425"),
                TipoEstado.RECIBIDO,
                LocalDate.now(),
                LocalDate.of(2022, 12, 12),
                LocalDate.of(2022, 12, 13),
                120.5,
                UsuarioRepositoryImpl(UsuarioDao).findById(1)!!
            )
            val pedido = PedidosRepositoryImpl(PedidosDao, TareaDao).save(pedidoTest)

            val tareaTest = Tarea(
                0,
                UUID.fromString("192a7f86-c34d-43e3-ba77-8083a542f425"),
                ProductosRepositoryImpl(ProductoDao).findById(1)!!,
                20.0,
                "Personalizacion",
                UsuarioRepositoryImpl(UsuarioDao).findById(1)!!,
                TurnosRepositoryImpl(TurnoDao).findById(1)!!,
                true,
                MaquinaEncordadoraRepositoryImpl(MaquinaEncordarDao).findById(1)!!,
                null,
                PedidosRepositoryImpl(PedidosDao, TareaDao).findById(1)!!
            )
            val tarea = TareasRepositoryImpl(TareaDao).save(tareaTest)
        }
    }
}