package controller

import config.AppConfig
import db.*
import entities.*
import models.*
import models.enums.TipoEstado
import models.enums.TipoPerfil
import org.junit.jupiter.api.*

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertAll
import repository.MaquinaEncordarRepository.MaquinaEncordadoraRepositoryImpl
import repository.MaquinaPersonalizacionRepository.MaquinaPersonalizacionRepositoryImpl
import repository.PedidosRepository.PedidosRepositoryImpl
import repository.ProductosRepository.ProductosRepositoryImpl
import repository.TareasRepository.TareasRepositoryImpl
import repository.TurnosRepository.TurnosRepositoryImpl
import repository.UsuarioRepository.UsuarioRepositoryImpl
import services.Password
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ControladorTest {
    val turnoTest = Turno(
        1,
        UUID.fromString("492a7f86-c34d-43e3-ba77-8083a542f427"),
        LocalDateTime.of(2022, 12, 5, 8, 0),
        LocalDateTime.of(2022, 12, 5, 10, 0)
    )

    val usuarioTest = Usuario(
        1,
        UUID.fromString("492a7f86-c34d-43e3-ba77-80834542f427"),
        "Administrador",
        "Prueba",
        "admin@admin.com",
        Password().encriptar("1234"),
        TipoPerfil.ADMINISTRADOR,
        TurnosRepositoryImpl(TurnoDao).findById(1)
    )

    val controlador =
        Controlador(
        MaquinaEncordadoraRepositoryImpl(MaquinaEncordarDao),
        MaquinaPersonalizacionRepositoryImpl(MaquinaPersonalizacionDao),
        PedidosRepositoryImpl(PedidosDao, TareaDao),
        ProductosRepositoryImpl(ProductoDao),
        TareasRepositoryImpl(TareaDao),
        UsuarioRepositoryImpl(UsuarioDao),
        TurnosRepositoryImpl(TurnoDao),
            Usuario(
                1,
                UUID.fromString("492a7f86-c34d-43e3-ba77-80834542f427"),
                "Administrador",
                "Prueba",
                "admin@admin.com",
                Password().encriptar("1234"),
                TipoPerfil.ADMINISTRADOR,
                TurnosRepositoryImpl(TurnoDao).findById(1)
            )
    )


    val maquinaPTest = Maquina.MaquinaPersonalizacion(
        1,
        UUID.fromString("492a7f86-c34d-43e3-ba77-8083a542f427"),
        "perso",
        "rojo",
        LocalDate.now(),
        true,
        1.0,
        10.4
    )
    val maquinaETest = Maquina.MaquinaEncordadora(
        1,
        UUID.fromString("492a7f86-c34d-43e3-ba77-8083a542f434"),
        "nadal",
        "rojo",
        LocalDate.now(),
        true,
        20.0,
        12.4
    )

    val pedidoTest = UsuarioRepositoryImpl(UsuarioDao).findById(1)?.let {
        Pedidos(
        1,
        UUID.fromString("492a7f86-c34d-43e3-ba77-8083a342f434"),
        TipoEstado.EN_PROCESO,
        LocalDate.now(),
        LocalDate.of(2022, 12, 6),
        null,
        120.5,
            it,
    )
    }

    val productoTest = Producto(
        1,
        UUID.fromString("492b7f86-c34d-43e3-ba77-8083a542f434"),
        "Wilson",
        "raqueta",
        20.2,
        12
    )

    val tareaTest = ProductosRepositoryImpl(ProductoDao).findById(1)?.let {
        Tarea(
        1,
        UUID.fromString("492b7f86-c34d-43e3-bc67-8083a542f434"),
            it,
        20.0,
        "Personalizacion",
        UsuarioRepositoryImpl(UsuarioDao).findById(1)!!,
        TurnosRepositoryImpl(TurnoDao).findById(1)!!,
        true,
        MaquinaEncordadoraRepositoryImpl(MaquinaEncordarDao).findById(1),
        null,
        PedidosRepositoryImpl(PedidosDao, TareaDao).findById(1)!!
    )
    }


    @Order(1)
    @Test
    fun listarMaquinasPerso() {
        val listarMaquinasPerso = controlador.listarMaquinasPerso()
        assertAll(
            { assertFalse(listarMaquinasPerso.isEmpty())},
            { assertEquals(listarMaquinasPerso.first().numSerie, maquinaPTest.numSerie) },
            { assertEquals(listarMaquinasPerso.first().marca, maquinaPTest.marca) },
            { assertEquals(listarMaquinasPerso.first().modelo, maquinaPTest.modelo) },
            { assertEquals(listarMaquinasPerso.first().balance, maquinaPTest.balance) },
            { assertEquals(listarMaquinasPerso.first().fechaAdquisicion, maquinaPTest.fechaAdquisicion) },
            { assertEquals(listarMaquinasPerso.first().rigidez, maquinaPTest.rigidez) },
            { assertEquals(listarMaquinasPerso.first().swingweight, maquinaPTest.swingweight)},
            { assertEquals(1, listarMaquinasPerso.size) }
        )
    }

    @Order(1)
    @Test
    fun encontrarMaquinaPersoID() {
        val testID = controlador.encontrarMaquinaPersoID(maquinaPTest.id)
        assertAll(
            { assertEquals(testID!!.numSerie, maquinaPTest.numSerie) },
            { assertEquals(testID!!.marca, maquinaPTest.marca) },
            { assertEquals(testID!!.modelo, maquinaPTest.modelo) },
            { assertEquals(testID!!.balance, maquinaPTest.balance) },
            { assertEquals(testID!!.fechaAdquisicion, maquinaPTest.fechaAdquisicion) },
            { assertEquals(testID!!.rigidez, maquinaPTest.rigidez) },
            { assertEquals(testID!!.swingweight, maquinaPTest.swingweight)}
        )
    }

    @Order(1)
    @Test
    fun encontrarMaquinaPersoUUID() {
        val testID = controlador.encontrarMaquinaPersoUUID(maquinaPTest.numSerie)
        assertAll(
            { assertEquals(testID!!.numSerie, maquinaPTest.numSerie) },
            { assertEquals(testID!!.marca, maquinaPTest.marca) },
            { assertEquals(testID!!.modelo, maquinaPTest.modelo) },
            { assertEquals(testID!!.balance, maquinaPTest.balance) },
            { assertEquals(testID!!.fechaAdquisicion, maquinaPTest.fechaAdquisicion) },
            { assertEquals(testID!!.rigidez, maquinaPTest.rigidez) },
            { assertEquals(testID!!.swingweight, maquinaPTest.swingweight)}
        )
    }

    @Order(1)
    @Test
    fun guardarMaquinaPerso() {
        val testSave = controlador.guardarMaquinaPerso(maquinaPTest)
        assertAll(
            { assertEquals(testSave.numSerie, maquinaPTest.numSerie) },
            { assertEquals(testSave.marca, maquinaPTest.marca) },
            { assertEquals(testSave.modelo, maquinaPTest.modelo) },
            { assertEquals(testSave.balance, maquinaPTest.balance) },
            { assertEquals(testSave.fechaAdquisicion, maquinaPTest.fechaAdquisicion) },
            { assertEquals(testSave.rigidez, maquinaPTest.rigidez) },
            { assertEquals(testSave.swingweight, maquinaPTest.swingweight)}
        )
    }

    @Order(5)
    @Test
    fun borrarMaquinaPerso() {
        val testDelete = controlador.borrarMaquinaPerso(maquinaPTest)
        assertAll(
            { assertTrue(testDelete) },
        )
    }

    @Order(1)
    @Test
    fun listarMaquinasEncordar() {
        val test = controlador.listarMaquinasEncordar()
        assertAll(
            { assertFalse(test.isEmpty()) },
            { assertEquals(test.first().numSerie, maquinaETest.numSerie) },
            { assertEquals(test.first().marca, maquinaETest.marca) },
            { assertEquals(test.first().modelo, maquinaETest.modelo) },
            { assertEquals(test.first().automatico, maquinaETest.automatico) },
            { assertEquals(test.first().fechaAdquisicion, maquinaETest.fechaAdquisicion) },
            { assertEquals(test.first().tensionMaxima, maquinaETest.tensionMaxima) },
            { assertEquals(test.first().tensionMinima, maquinaETest.tensionMinima) },
            { assertEquals(test.size, 1) }
        )
    }

    @Order(1)
    @Test
    fun encontrarMaquinaEncordarID() {
        val testID = controlador.encontrarMaquinaEncordarID(maquinaETest.id)
        assertAll(
            { assertEquals(testID!!.numSerie, maquinaETest.numSerie) },
            { assertEquals(testID!!.marca, maquinaETest.marca) },
            { assertEquals(testID!!.modelo, maquinaETest.modelo) },
            { assertEquals(testID!!.automatico, maquinaETest.automatico) },
            { assertEquals(testID!!.fechaAdquisicion, maquinaETest.fechaAdquisicion) },
            { assertEquals(testID!!.tensionMaxima, maquinaETest.tensionMaxima) },
            { assertEquals(testID!!.tensionMinima, maquinaETest.tensionMinima) }
        )
    }

    @Order(1)
    @Test
    fun encontrarMaquinaEncordarUUID() {
        val testUUID = controlador.encontrarMaquinaEncordarUUID(maquinaETest.numSerie)
        assertAll(
            { assertEquals(testUUID!!.numSerie, maquinaETest.numSerie) },
            { assertEquals(testUUID!!.marca, maquinaETest.marca) },
            { assertEquals(testUUID!!.modelo, maquinaETest.modelo) },
            { assertEquals(testUUID!!.automatico, maquinaETest.automatico) },
            { assertEquals(testUUID!!.fechaAdquisicion, maquinaETest.fechaAdquisicion) },
            { assertEquals(testUUID!!.tensionMaxima, maquinaETest.tensionMaxima) },
            { assertEquals(testUUID!!.tensionMinima, maquinaETest.tensionMinima) }
        )
    }

    @Order(1)
    @Test
    fun guardarMaquinaEncordar() {
        val testSave = controlador.guardarMaquinaEncordar(maquinaETest)
        assertAll(
            { assertEquals(testSave.numSerie, maquinaETest.numSerie) },
            { assertEquals(testSave.marca, maquinaETest.marca) },
            { assertEquals(testSave.modelo, maquinaETest.modelo) },
            { assertEquals(testSave.automatico, maquinaETest.automatico) },
            { assertEquals(testSave.fechaAdquisicion, maquinaETest.fechaAdquisicion) },
            { assertEquals(testSave.tensionMaxima, maquinaETest.tensionMaxima) },
            { assertEquals(testSave.tensionMinima, maquinaETest.tensionMinima) }
        )
    }

    @Order(5)
    @Test
    fun borrarMaquinaEncordar() {
        val testDelete = controlador.borrarMaquinaEncordar(maquinaETest)
        assertAll(
            { assertTrue(testDelete) },
        )
    }

    @Order(1)
    @Test
    fun listarPedidos() {
        val test = controlador.listarPedidos()
        assertAll(
            { assertFalse(test.isEmpty()) },
            { assertEquals(test.first().uuid, pedidoTest!!.uuid) },
            { assertEquals(test.first().estado, pedidoTest!!.estado) },
            { assertEquals(test.first().usuario, pedidoTest!!.usuario) },
            { assertEquals(test.first().fechaEntrada, pedidoTest!!.fechaEntrada) },
            { assertEquals(test.first().fechaEntrega, pedidoTest!!.fechaEntrega) },
            { assertEquals(test.first().fechaSalidaProgramada, pedidoTest!!.fechaSalidaProgramada) },
            { assertEquals(test.size, 1) }
        )
    }

    @Order(1)
    @Test
    fun encontrarPedidoID() {
        val test = controlador.encontrarPedidoID(pedidoTest!!.id)
        assertAll(
            { assertEquals(test!!.uuid, pedidoTest.uuid) },
            { assertEquals(test!!.estado, pedidoTest.estado) },
            { assertEquals(test!!.usuario, pedidoTest.usuario) },
            { assertEquals(test!!.fechaEntrada, pedidoTest.fechaEntrada) },
            { assertEquals(test!!.fechaEntrega, pedidoTest.fechaEntrega) },
            { assertEquals(test!!.fechaSalidaProgramada, pedidoTest.fechaSalidaProgramada) },
        )
    }

    @Order(1)
    @Test
    fun encontrarPedidoUUID() {
        val test = controlador.encontrarPedidoUUID(pedidoTest!!.uuid)
        assertAll(
            { assertEquals(test!!.uuid, pedidoTest.uuid) },
            { assertEquals(test!!.estado, pedidoTest.estado) },
            { assertEquals(test!!.usuario, pedidoTest.usuario) },
            { assertEquals(test!!.fechaEntrada, pedidoTest.fechaEntrada) },
            { assertEquals(test!!.fechaEntrega, pedidoTest.fechaEntrega) },
            { assertEquals(test!!.fechaSalidaProgramada, pedidoTest.fechaSalidaProgramada) },
        )
    }

    @Order(1)
    @Test
    fun guardarPedido() {
        val test = controlador.guardarPedido(pedidoTest!!)
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
    fun borrarPedido() {
        val testDelete = controlador.borrarPedido(pedidoTest!!)
        assertAll(
            { assertTrue(testDelete) },
        )
    }

    @Order(1)
    @Test
    fun listarProductos() {
        val test = controlador.listarProductos()
        assertAll(
            { assertFalse(test.isEmpty()) },
            { assertEquals(test.first().uuid, productoTest.uuid) },
            { assertEquals(test.first().marca, productoTest.marca) },
            { assertEquals(test.first().modelo, productoTest.modelo) },
            { assertEquals(test.first().precio, productoTest.precio) },
            { assertEquals(test.first().stock, productoTest.stock) },
            { assertEquals(test.size, 1) }
        )
    }

    @Order(1)
    @Test
    fun encontrarProductoID() {
        val testID = controlador.encontrarProductoID(productoTest.id)
        assertAll(
            { assertEquals(testID!!.uuid, productoTest.uuid) },
            { assertEquals(testID!!.marca, productoTest.marca) },
            { assertEquals(testID!!.modelo, productoTest.modelo) },
            { assertEquals(testID!!.precio, productoTest.precio) },
            { assertEquals(testID!!.stock, productoTest.stock) },
        )
    }

    @Order(1)
    @Test
    fun encontrarProductoUUID() {
        val testUUID = controlador.encontrarProductoUUID(productoTest.uuid)
        assertAll(
            { assertEquals(testUUID!!.uuid, productoTest.uuid) },
            { assertEquals(testUUID!!.marca, productoTest.marca) },
            { assertEquals(testUUID!!.modelo, productoTest.modelo) },
            { assertEquals(testUUID!!.precio, productoTest.precio) },
            { assertEquals(testUUID!!.stock, productoTest.stock) },
        )
    }

    @Order(1)
    @Test
    fun guardarProducto() {
        val testSave = controlador.guardarProducto(productoTest)
        assertAll(
            { assertEquals(testSave!!.uuid, productoTest.uuid) },
            { assertEquals(testSave!!.marca, productoTest.marca) },
            { assertEquals(testSave!!.modelo, productoTest.modelo) },
            { assertEquals(testSave!!.precio, productoTest.precio) },
            { assertEquals(testSave!!.stock, productoTest.stock) },
        )
    }


    @Order(3)
    @Test
    fun borrarProducto() {
        val testDelete = controlador.borrarProducto(productoTest)
        assertAll(
            { assertTrue(testDelete) },
        )
    }

    @Order(1)
    @Test
    fun listarTareas() {
        val test = controlador.listarTareas()
        assertAll(
            { assertFalse(test.isEmpty()) },
            { assertEquals(test.first().uuidTarea, tareaTest!!.uuidTarea) },
            { assertEquals(test.first().producto, tareaTest!!.producto) },
            { assertEquals(test.first().precio, tareaTest!!.precio) },
            { assertEquals(test.first().descripcion, tareaTest!!.descripcion) },
            { assertEquals(test.first().empleado, tareaTest!!.empleado) },
            { assertEquals(test.first().turno, tareaTest!!.turno) },
            { assertEquals(test.first().estadoCompletado, tareaTest!!.estadoCompletado) },
            { assertEquals(test.first().maquinaEncordar, tareaTest!!.maquinaEncordar) },
            { assertEquals(test.first().maquinaPersonalizacion, tareaTest!!.maquinaPersonalizacion) },
            { assertEquals(test.first().pedido, tareaTest!!.pedido) },
            { assertEquals(test.size, 1) }
        )
    }

    @Order(1)
    @Test
    fun encontrarTareaID() {
        val testID = controlador.encontrarTareaID(tareaTest!!.id)
        assertAll(
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

    @Order(1)
    @Test
    fun encontrarTareaUUID() {
        val testUUID = controlador.encontrarTareaUUID(tareaTest!!.uuidTarea)
        assertAll(
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

    @Order(1)
    @Test
    fun guardarTarea() {
        val testSave = controlador.guardarTarea(tareaTest!!)
        Assertions.assertAll(
            { assertEquals(testSave!!.uuidTarea, tareaTest.uuidTarea) },
            { assertEquals(testSave!!.producto, tareaTest.producto) },
            { assertEquals(testSave!!.precio, tareaTest.precio) },
            { assertEquals(testSave!!.descripcion, tareaTest.descripcion) },
            { assertEquals(testSave!!.empleado, tareaTest.empleado) },
            { assertEquals(testSave!!.turno, tareaTest.turno) },
            { assertEquals(testSave!!.estadoCompletado, tareaTest.estadoCompletado) },
            { assertEquals(testSave!!.maquinaEncordar, tareaTest.maquinaEncordar) },
            { assertEquals(testSave!!.maquinaPersonalizacion, tareaTest.maquinaPersonalizacion) },
            { assertEquals(testSave!!.pedido, tareaTest.pedido) },
        )
    }

    @Order(2)
    @Test
    fun borrarTarea() {
        val testDelete = controlador.borrarTarea(tareaTest!!)
        Assertions.assertAll(
            { assertTrue(testDelete) }
        )
    }

    @Order(1)
    @Test
    fun listarUsuarios() {
        val test = controlador.listarUsuarios()
        assertAll(
            { assertFalse(test.isEmpty()) },
            { assertEquals(test.first().uuid, usuarioTest.uuid) },
            { assertEquals(test.first().nombre, usuarioTest.nombre) },
            { assertEquals(test.first().apellido, usuarioTest.apellido) },
            { assertEquals(test.first().email, usuarioTest.email) },
            { assertEquals(1, test.size) }
        )
    }

    @Order(1)
    @Test
    fun encontrarUsuarioID() {
        val testId = controlador.encontrarUsuarioID(usuarioTest.id)
        assertAll(
            { assertEquals(testId!!.uuid, usuarioTest.uuid) },
            { assertEquals(testId!!.nombre, usuarioTest.nombre) },
            { assertEquals(testId!!.apellido, usuarioTest.apellido) },
            { assertEquals(testId!!.email, usuarioTest.email) },
        )
    }

    @Order(1)
    @Test
    fun encontrarUsuarioUUID() {
        val testUUId = controlador.encontrarUsuarioUUID(usuarioTest.uuid)
        assertAll(
            { assertEquals(testUUId!!.uuid, usuarioTest.uuid) },
            { assertEquals(testUUId!!.nombre, usuarioTest.nombre) },
            { assertEquals(testUUId!!.apellido, usuarioTest.apellido) },
            { assertEquals(testUUId!!.email, usuarioTest.email) },
        )
    }

    @Order(1)
    @Test
    fun guardarUsuario() {
        val testSave = controlador.guardarUsuario(usuarioTest)
        assertAll(
            { assertEquals(testSave.uuid, usuarioTest.uuid) },
            { assertEquals(testSave.nombre, usuarioTest.nombre) },
            { assertEquals(testSave.apellido, usuarioTest.apellido) },
            { assertEquals(testSave.email, usuarioTest.email) },
        )
    }

    @Order(6)
    @Test
    fun borrarUsuario() {
        val testDelete = controlador.borrarUsuario(usuarioTest)
        assertAll(
            { assertTrue(testDelete) },
        )
    }

    @Order(1)
    @Test
    fun listarTurnos() {
        val test = controlador.listarTurnos()
        assertAll(
            { assertFalse(test.isEmpty()) },
            { assertEquals(test.first().uuidTurno, turnoTest.uuidTurno) },
            { assertEquals(test.first().fechaInicio, turnoTest.fechaInicio) },
            { assertEquals(test.first().fechaFin, turnoTest.fechaFin) },
            { assertEquals(test.size, 1) }
        )
    }

    @Order(1)
    @Test
    fun encontrarTurnoID() {
        val testId = controlador.encontrarTurnoID(turnoTest.id)
        assertAll(
            { assertEquals(testId!!.uuidTurno, turnoTest.uuidTurno) },
            { assertEquals(testId!!.fechaInicio, turnoTest.fechaInicio) },
            { assertEquals(testId!!.fechaFin, turnoTest.fechaFin) }
        )
    }

    @Order(1)
    @Test
    fun encontrarTurnoUUID() {
        val testUUId = controlador.encontrarTurnoUUID(turnoTest.uuidTurno)
        assertAll(
            { assertEquals(testUUId!!.uuidTurno, turnoTest.uuidTurno) },
            { assertEquals(testUUId!!.fechaInicio, turnoTest.fechaInicio) },
            { assertEquals(testUUId!!.fechaFin, turnoTest.fechaFin) }
        )
    }

    @Order(1)
    @Test
    fun guardarTurno() {
        val testSave = controlador.guardarTurno(turnoTest)
        assertAll(
            { assertEquals(testSave.uuidTurno, turnoTest.uuidTurno) },
            { assertEquals(testSave.fechaInicio, turnoTest.fechaInicio) },
            { assertEquals(testSave.fechaFin, turnoTest.fechaFin) }
        )
    }

    @Order(7)
    @Test
    fun borrarTurno() {
        val testDelete = controlador.borrarTurno(turnoTest)
        assertAll(
            { assertTrue(testDelete) }
        )
    }


    companion object{
        @JvmStatic
        @BeforeAll
        fun setUp(): Unit {
            DataBaseManager.init(AppConfig.TESTCONTROLADOR)
            val turnoTest = Turno(
                0,
                UUID.fromString("492a7f86-c34d-43e3-ba77-8083a542f427"),
                LocalDateTime.of(2022, 12, 5, 8, 0),
                LocalDateTime.of(2022, 12, 5, 10, 0)
            )
            TurnosRepositoryImpl(TurnoDao).save(turnoTest)

            val meterAdmin =   Usuario(
                0,
                UUID.fromString("492a7f86-c34d-43e3-ba77-80834542f427"),
                "Administrador",
                "Prueba",
                "admin@admin.com",
                Password().encriptar("1234"),
                TipoPerfil.ADMINISTRADOR,
                TurnosRepositoryImpl(TurnoDao).findById(1)
            )
            UsuarioRepositoryImpl(UsuarioDao).save(meterAdmin)

            val controlador = Controlador(
                MaquinaEncordadoraRepositoryImpl(MaquinaEncordarDao),
                MaquinaPersonalizacionRepositoryImpl(MaquinaPersonalizacionDao),
                PedidosRepositoryImpl(PedidosDao, TareaDao),
                ProductosRepositoryImpl(ProductoDao),
                TareasRepositoryImpl(TareaDao),
                UsuarioRepositoryImpl(UsuarioDao),
                TurnosRepositoryImpl(TurnoDao),
                UsuarioRepositoryImpl(UsuarioDao).findById(1)!!
            )

            val maquinaPTest = Maquina.MaquinaPersonalizacion(
                0,
                UUID.fromString("492a7f86-c34d-43e3-ba77-8083a542f427"),
                "perso",
                "rojo",
                LocalDate.now(),
                true,
                1.0,
                10.4
            )

            val maquinaP = MaquinaPersonalizacionRepositoryImpl(MaquinaPersonalizacionDao).save(maquinaPTest)
            val maquinaETest = Maquina.MaquinaEncordadora(
                0,
                UUID.fromString("492a7f86-c34d-43e3-ba77-8083a542f434"),
                "nadal",
                "rojo",
                LocalDate.now(),
                true,
                20.0,
                12.4
            )
            val maquinaE = controlador.guardarMaquinaEncordar(maquinaETest)
            val pedido = Pedidos(
                0,
                UUID.fromString("492a7f86-c34d-43e3-ba77-8083a342f434"),
                TipoEstado.EN_PROCESO,
                LocalDate.now(),
                LocalDate.of(2022, 12, 6),
                null,
                120.5,
                UsuarioRepositoryImpl(UsuarioDao).findById(1)!!,
            )
            val pedidoTest = controlador.guardarPedido(pedido)
            val productoTest = Producto(
                0,
                UUID.fromString("492b7f86-c34d-43e3-ba77-8083a542f434"),
                "Wilson",
                "raqueta",
                20.2,
                12
            )
            val producto = controlador.guardarProducto(productoTest)
            val tareaTest = Tarea(
                0,
                UUID.fromString("492b7f86-c34d-43e3-bc67-8083a542f434"),
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
            val tarea = controlador.guardarTarea(tareaTest)
        }

    }
}