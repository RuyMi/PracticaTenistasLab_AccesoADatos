import config.AppConfig
import controller.Controlador
import db.*
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


/**
 * Main
 *
 * @param args
 */
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
            TipoPerfil.ADMINISTRADOR,
            Turno(
                1,
                UUID.randomUUID(),
                LocalDateTime.now(),
                LocalDateTime.now()
            ))
    )
    getTurnos().forEach { controlador.guardarTurno(it) }
    val listaTurnos = controlador.listarTurnos()
    listaTurnos.forEach{ println(it)}

    getUsuarios().forEach { controlador.guardarUsuario(it!!) }
    val listaUsuarios = controlador.listarUsuarios()
    listaUsuarios.forEach{ println(it)}

    getMaquinasEncordar().forEach{ controlador.guardarMaquinaEncordar(it)}
    val listaMaquinas = controlador.listarMaquinasEncordar()
    listaMaquinas.forEach{ println(it)}

    getMaquinasPersonalizacion().forEach { controlador.guardarMaquinaPerso(it)}
    val listaMaquinasPerso = controlador.listarMaquinasPerso()
    listaMaquinasPerso.forEach{ println(it)}

    getProductos().forEach { controlador.guardarProducto(it)}
    val listaProductos = controlador.listarProductos()
    listaProductos.forEach{ println(it)}


}

/**
 * Init data base
 *
 */
fun initDataBase() {
    val appConfig = AppConfig.fromPropertiesFile("src/main/resources/config.properties")
    println("Configuración: $appConfig")

    // Iniciamos la base de datos con la configuracion que hemos leido
    DataBaseManager.init(appConfig)

}