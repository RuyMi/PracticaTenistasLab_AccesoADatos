import config.AppConfig
import controller.Controlador
import db.DataBaseManager
import entities.*
import kotlinx.coroutines.runBlocking
import models.Maquina
import models.Turno
import models.Usuario
import models.enums.TipoPerfil
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


fun main(args: Array<String>) = runBlocking {
    initDataBase()
    val controlador = Controlador(
        MaquinaEncordadoraRepositoryImpl(MaquinaEncordarDao),
        MaquinaPersonalizacionRepositoryImpl(MaquinaPersonalizacionDao),
        PedidosRepositoryImpl(PedidosDao, TareaDao),
        ProductosRepositoryImpl(ProductoDao),
        TareasRepositoryImpl(TareaDao),
        UsuarioRepositoryImpl(UsuarioDao),
        TurnosRepositoryImpl(TurnoDao),
        Usuario(0,
            UUID.randomUUID(),
            "Ruben",
            "García",
            "fewi",
            Password().SHA256("prueba"),
            TipoPerfil.ENCORDADOR,
            Turno(
                1,
                UUID.randomUUID(),
                LocalDateTime.now(),
                LocalDateTime.now()
            ))
    )
    val maquina = MaquinaPersonalizacionDao
    val repo = MaquinaPersonalizacionRepositoryImpl(maquina)
    val prueba = Maquina.MaquinaPersonalizacion(
        0,
        numSerie = UUID.randomUUID(),
        marca = "Hola",
        modelo = "hola",
        fechaAdquisicion = LocalDate.now(),
        swingweight = true,
        balance = 20.0,
        rigidez = 20.0
    )
    val usuario = Usuario(
        1,
        UUID.randomUUID(),
        "Ruben",
        "García",
        "fewi",
        Password().SHA256("prueba"),
        TipoPerfil.ENCORDADOR,
        Turno(
        1,
            UUID.randomUUID(),
            LocalDateTime.now(),
            LocalDateTime.now()
        )
    )
    controlador.guardarTurno(  Turno(
        1,
        UUID.randomUUID(),
        LocalDateTime.now(),
        LocalDateTime.now()
    ))
    controlador.guardarUsuario(usuario)

    val usuariorepo = controlador.listarUsuarios()
    println(usuariorepo)
}

fun initDataBase() {
    val appConfig = AppConfig.fromPropertiesFile("src/main/resources/config.properties")
    println("Configuración: $appConfig")

    // Iniciamos la base de datos con la configuracion que hemos leido
    DataBaseManager.init(appConfig)

}