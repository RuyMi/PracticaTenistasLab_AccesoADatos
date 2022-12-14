package db

import entities.*
import models.*
import models.enums.TipoEstado
import models.enums.TipoPerfil
import repository.MaquinaEncordarRepository.MaquinaEncordadoraRepositoryImpl
import repository.MaquinaPersonalizacionRepository.MaquinaPersonalizacionRepositoryImpl
import repository.PedidosRepository.PedidosRepositoryImpl
import repository.ProductosRepository.ProductosRepositoryImpl
import repository.TurnosRepository.TurnosRepositoryImpl
import repository.UsuarioRepository.UsuarioRepositoryImpl
import services.Password
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

fun getTurnos(): List<Turno> {
    return listOf(
        Turno(
            0,
            UUID.randomUUID(),
            LocalDateTime.of(2022, 12, 5, 8, 0),
            LocalDateTime.of(2022, 12, 5, 10, 0)
        ),
        Turno(
            0,
            UUID.randomUUID(),
            LocalDateTime.of(2022, 12, 5, 10, 0),
            LocalDateTime.of(2022, 12, 5, 12, 0)
        ),
        Turno(
            0,
            UUID.randomUUID(),
            LocalDateTime.of(2022, 12, 5, 12, 0),
            LocalDateTime.of(2022, 12, 5, 14, 0)
        ),
        Turno(
            0,
            UUID.randomUUID(),
            LocalDateTime.of(2022, 12, 5, 14, 0),
            LocalDateTime.of(2022, 12, 5, 16, 0)
        )
    )
}

fun getUsuarios(): List<Usuario?> {
return listOf(
    TurnosRepositoryImpl(TurnoDao).findById(1)?.let {
        Usuario(
            0,
            UUID.randomUUID(),
            "Mario",
            "Sánchez",
            "mario.sanchez@gmail.com",
            Password().encriptar("marioSanchez"),
            TipoPerfil.ENCORDADOR,
            it
        )
    },
    Usuario(
        0,
        UUID.randomUUID(),
        "Andrés",
        "Márquez",
        "andres.marquez@gmail.com",
        Password().encriptar("andresMarquez"),
        TipoPerfil.USUARIO,
        null
    ),
    TurnosRepositoryImpl(TurnoDao).findById(2)?.let {
        Usuario(
            0,
            UUID.randomUUID(),
            "Rubén",
            "García-Redondo",
            "rubengrm@gmail.com",
            Password().encriptar("rubengrm"),
            TipoPerfil.ENCORDADOR,
            it
        )
    },
    TurnosRepositoryImpl(TurnoDao).findById(3)?.let {
        Usuario(
            0,
            UUID.randomUUID(),
            "Álvaro",
            "Yubero",
            "alvaro.yubero@gmail.com",
            Password().encriptar("alvaroYubero"),
            TipoPerfil.ENCORDADOR,
            it
        )
    }
)
}

fun getMaquinasEncordar(): List<Maquina.MaquinaEncordadora> {
    return listOf(
        Maquina.MaquinaEncordadora(
            0,
            UUID.randomUUID(),
            "nadal",
            "rojo",
            LocalDate.now(),
            true,
            20.0,
            12.4
        ),
        Maquina.MaquinaEncordadora(
            0,
            UUID.randomUUID(),
            "nike",
            "verde",
            LocalDate.now(),
            true,
            19.2,
            10.4
        ),
        Maquina.MaquinaEncordadora(
            0,
            UUID.randomUUID(),
            "Adidas",
            "Azul",
            LocalDate.now(),
            false,
            22.0,
            13.1
        ),
        Maquina.MaquinaEncordadora(
            0,
            UUID.randomUUID(),
            "Apple",
            "morado",
            LocalDate.now(),
            true,
            25.0,
            9.1
        )
    )
}

fun getMaquinasPersonalizacion(): List<Maquina.MaquinaPersonalizacion>{
    return listOf(
        Maquina.MaquinaPersonalizacion(
            0,
            UUID.randomUUID(),
            "perso1",
            "rojo",
            LocalDate.now(),
            true,
            1.0,
            10.4
        ),
        Maquina.MaquinaPersonalizacion(
            0,
            UUID.randomUUID(),
            "nike",
            "verde",
            LocalDate.now(),
            true,
            19.2,
            10.4
        ),
        Maquina.MaquinaPersonalizacion(
            0,
            UUID.randomUUID(),
            "Adidas",
            "Azul",
            LocalDate.now(),
            false,
            22.0,
            13.1
        ),
        Maquina.MaquinaPersonalizacion(
            0,
            UUID.randomUUID(),
            "Apple",
            "morado",
            LocalDate.now(),
            true,
            25.0,
            9.1
        )
    )
}

fun getPedidos(): List<Pedidos>{
    return listOf(
        Pedidos(
            0,
            UUID.randomUUID(),
            TipoEstado.EN_PROCESO,
            LocalDate.now(),
            LocalDate.of(2022, 12, 6),
            null,
            120.5,
            UsuarioRepositoryImpl(UsuarioDao).findById(2)!!,
        ),
        Pedidos(
            0,
            UUID.randomUUID(),
            TipoEstado.RECIBIDO,
            LocalDate.now(),
            LocalDate.of(2022, 12, 6),
            LocalDate.of(2022, 12, 7),
            120.5,
            UsuarioRepositoryImpl(UsuarioDao).findById(2)!!,
        ),
        Pedidos(
            0,
            UUID.randomUUID(),
            TipoEstado.TERMINADO,
            LocalDate.now(),
            LocalDate.of(2022, 12, 6),
            null,
            120.5,
            UsuarioRepositoryImpl(UsuarioDao).findById(2)!!,
        ),
        Pedidos(
            0,
            UUID.randomUUID(),
            TipoEstado.EN_PROCESO,
            LocalDate.now(),
            LocalDate.of(2022, 12, 6),
            null,
            120.5,
            UsuarioRepositoryImpl(UsuarioDao).findById(2)!!,
        )
    )
}

fun getProductos(): List<Producto>{
    return listOf(
        Producto(
            0,
            UUID.randomUUID(),
            "Wilson",
            "raqueta",
            20.2,
            12
        ),
        Producto(
            0,
            UUID.randomUUID(),
            "hola",
            "Overgrips",
            20.2,
            12
        ),
        Producto(
            0,
            UUID.randomUUID(),
            "adios",
            "grips",
            20.2,
            12
        )
    )
}

fun getTareas(): List<Tarea>{
    return listOf(
        Tarea(
          0,
            UUID.randomUUID(),
            ProductosRepositoryImpl(ProductoDao).findById(1)!!,
            20.0,
            "Personalizacion",
            UsuarioRepositoryImpl(UsuarioDao).findById(3)!!,
            TurnosRepositoryImpl(TurnoDao).findById(1)!!,
            true,
            MaquinaEncordadoraRepositoryImpl(MaquinaEncordarDao).findById(1)!!,
            null,
            PedidosRepositoryImpl(PedidosDao, TareaDao).findById(1)!!
        ),
        Tarea(
            0,
            UUID.randomUUID(),
            ProductosRepositoryImpl(ProductoDao).findById(3)!!,
            20.0,
            "Personalizacion",
            UsuarioRepositoryImpl(UsuarioDao).findById(2)!!,
            TurnosRepositoryImpl(TurnoDao).findById(3)!!,
            true,
            MaquinaEncordadoraRepositoryImpl(MaquinaEncordarDao).findById(2)!!,
            null,
            PedidosRepositoryImpl(PedidosDao, TareaDao).findById(3)!!
        ),
        Tarea(
            0,
            UUID.randomUUID(),
            ProductosRepositoryImpl(ProductoDao).findById(2)!!,
            20.0,
            "Personalizacion",
            UsuarioRepositoryImpl(UsuarioDao).findById(2)!!,
            TurnosRepositoryImpl(TurnoDao).findById(2)!!,
            true,
            MaquinaEncordadoraRepositoryImpl(MaquinaEncordarDao).findById(3)!!,
            null,
            PedidosRepositoryImpl(PedidosDao, TareaDao).findById(3)!!
        ),
        Tarea(
            0,
            UUID.randomUUID(),
            ProductosRepositoryImpl(ProductoDao).findById(1)!!,
            20.0,
            "Personalizacion",
            UsuarioRepositoryImpl(UsuarioDao).findById(3)!!,
            TurnosRepositoryImpl(TurnoDao).findById(3)!!,
            true,
            null,
            MaquinaPersonalizacionRepositoryImpl(MaquinaPersonalizacionDao).findById(1)!!,
            PedidosRepositoryImpl(PedidosDao, TareaDao).findById(3)!!
        )
    )
}

// Tarea, Producto, Pedido, MaquinaE, MaquinaP, Usuario, Turno