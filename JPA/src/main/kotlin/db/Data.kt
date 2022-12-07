package db


import models.*
import models.enums.TipoEstado
import models.enums.TipoPerfil
import repositories.MaquinaEncordarRepository.MaquinaEncordarRepositoryImpl
import repositories.MaquinaPersonalizacionRepository.MaquinaPersonalizacionRepositoryImpl
import repositories.PedidosRepository.PedidosRepositoryImpl
import repositories.ProductosRepository.ProductosRepositoryImpl
import repositories.TurnosRepository.TurnosRepositoryImpl
import repositories.UsuarioRepository.UsuarioRepositoryImpl
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
    TurnosRepositoryImpl().findById(1)?.let {
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
    TurnosRepositoryImpl().findById(2)?.let {
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
    TurnosRepositoryImpl().findById(3)?.let {
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
            UsuarioRepositoryImpl().findById(2)!!,
        ),
        Pedidos(
            0,
            UUID.randomUUID(),
            TipoEstado.RECIBIDO,
            LocalDate.now(),
            LocalDate.of(2022, 12, 6),
            LocalDate.of(2022, 12, 7),
            120.5,
            UsuarioRepositoryImpl().findById(2)!!,
        ),
        Pedidos(
            0,
            UUID.randomUUID(),
            TipoEstado.TERMINADO,
            LocalDate.now(),
            LocalDate.of(2022, 12, 6),
            null,
            120.5,
            UsuarioRepositoryImpl().findById(2)!!,
        ),
        Pedidos(
            0,
            UUID.randomUUID(),
            TipoEstado.EN_PROCESO,
            LocalDate.now(),
            LocalDate.of(2022, 12, 6),
            null,
            120.5,
            UsuarioRepositoryImpl().findById(2)!!,
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
            ProductosRepositoryImpl().findById(1)!!,
            20.0,
            "Personalizacion",
            UsuarioRepositoryImpl().findById(3)!!,
            TurnosRepositoryImpl().findById(1)!!,
            true,
            MaquinaEncordarRepositoryImpl().findById(1)!!,
            null,
            PedidosRepositoryImpl().findById(1)!!
        ),
        Tarea(
            0,
            UUID.randomUUID(),
            ProductosRepositoryImpl().findById(3)!!,
            20.0,
            "Personalizacion",
            UsuarioRepositoryImpl().findById(2)!!,
            TurnosRepositoryImpl().findById(3)!!,
            true,
            MaquinaEncordarRepositoryImpl().findById(2)!!,
            null,
            PedidosRepositoryImpl().findById(3)!!
        ),
        Tarea(
            0,
            UUID.randomUUID(),
            ProductosRepositoryImpl().findById(2)!!,
            20.0,
            "Personalizacion",
            UsuarioRepositoryImpl().findById(2)!!,
            TurnosRepositoryImpl().findById(2)!!,
            true,
            MaquinaEncordarRepositoryImpl().findById(3)!!,
            null,
            PedidosRepositoryImpl().findById(3)!!
        ),
        Tarea(
            0,
            UUID.randomUUID(),
            ProductosRepositoryImpl().findById(1)!!,
            20.0,
            "Personalizacion",
            UsuarioRepositoryImpl().findById(3)!!,
            TurnosRepositoryImpl().findById(3)!!,
            true,
            null,
            MaquinaPersonalizacionRepositoryImpl().findById(1)!!,
            PedidosRepositoryImpl().findById(3)!!
        )
    )
}

