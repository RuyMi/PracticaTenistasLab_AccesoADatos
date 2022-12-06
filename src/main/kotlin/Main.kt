import config.AppConfig
import controller.Controlador
import db.*
import entities.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
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
import java.util.*
import kotlin.system.exitProcess


/**
 * Main
 *
 * @param args
 */
fun main(args: Array<String>) = runBlocking {
    initDataBase()
    val usuarioActual = iniciarSesion()
    val controlador = Controlador(
        MaquinaEncordadoraRepositoryImpl(MaquinaEncordarDao),
        MaquinaPersonalizacionRepositoryImpl(MaquinaPersonalizacionDao),
        PedidosRepositoryImpl(PedidosDao, TareaDao),
        ProductosRepositoryImpl(ProductoDao),
        TareasRepositoryImpl(TareaDao),
        UsuarioRepositoryImpl(UsuarioDao),
        TurnosRepositoryImpl(TurnoDao),
        usuarioActual
    )
    mostrarMenuPrincipal(usuarioActual)
    meterDatos(controlador)


}

fun mostrarMenuPrincipal(usuario: Usuario) {
    when(usuario.perfil){
        TipoPerfil.ENCORDADOR ->{
            println("""
                Seleccione una de las siguientes opciones:
                1. Crear Pedido
                2. Modificar Pedido
                3. Crear Tarea
                4. Modificar Tarea
                5. Crear Turno (Bloqueado)
                6. Modificar Turno (Bloqueado)
                7. Crear Usuarios (Bloqueado)
                8. Modificar Usuarios (Bloqueado)
                9. Listar datos (Bloqueado)
                Cambiar. Cambiar usuario
                Exit. Salir del programa
            """.trimIndent())
        }
        TipoPerfil.USUARIO ->{
            println("""
                Seleccione una de las siguientes opciones:
                1. Crear Pedido
                2. Modificar Pedido 
                3. Crear Tarea (Bloqueado)
                4. Modificar Tarea (Bloqueado)
                5. Crear Turno (Bloqueado)
                6. Modificar Turno (Bloqueado)
                7. Crear Usuarios (Bloqueado)
                8. Modificar Usuarios (Bloqueado)
                9. Listar datos (Bloqueado)
                Cambiar. Cambiar usuario
                Exit. Salir del programa
            """.trimIndent())
        }
        TipoPerfil.ADMINISTRADOR ->{
            println("""
                Seleccione una de las siguientes opciones:
                1. Crear Pedido
                2. Modificar Pedido 
                3. Crear Tarea 
                4. Modificar Tarea 
                5. Crear Turno 
                6. Modificar Turno 
                7. Crear Usuarios 
                8. Modificar Usuarios 
                9. Listar datos 
                Cambiar. Cambiar usuario
                Exit. Salir del programa
            """.trimIndent())
        }
    }
}

fun iniciarSesion(): Usuario {
    println("Bienvenido al sistema, por favor introduzca su correo electronico y su contraseña para acceder")
    val usuario = readln()
    val password = Password().encriptar(readln())
    println(password)
    try{
        val coincidente = UsuarioRepositoryImpl(UsuarioDao).findAll().first { it.email == usuario && it.password == password }
        println("Bienvenido: ${coincidente.nombre} ${coincidente.apellido}, eres un ${coincidente.perfil}")
        return coincidente
    }catch (e: Exception){
        println("Usuario no válido... Saliendo del sistema")
        exitProcess(0)
    }


}

private fun meterDatos(controlador: Controlador) {
    getTurnos().forEach { controlador.guardarTurno(it) }
    val listaTurnos = controlador.listarTurnos()
    listaTurnos.forEach { println(it) }

    getUsuarios().forEach { controlador.guardarUsuario(it!!) }
    val listaUsuarios = controlador.listarUsuarios()
    listaUsuarios.forEach { println(it) }

    getMaquinasEncordar().forEach { controlador.guardarMaquinaEncordar(it) }
    val listaMaquinas = controlador.listarMaquinasEncordar()
    listaMaquinas.forEach { println(it) }

    getMaquinasPersonalizacion().forEach { controlador.guardarMaquinaPerso(it) }
    val listaMaquinasPerso = controlador.listarMaquinasPerso()
    listaMaquinasPerso.forEach { println(it) }

    getProductos().forEach { controlador.guardarProducto(it) }
    val listaProductos = controlador.listarProductos()
    listaProductos.forEach { println(it) }

    getPedidos().forEach { controlador.guardarPedido(it) }
    val listaPedidos = controlador.listarPedidos()
    listaPedidos.forEach { println(it) }

    getTareas().forEach { controlador.guardarTarea(it) }
    val listaTareas = controlador.listarTareas()
    listaTareas.forEach { println(it) }
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
    val meterAdmin =   Usuario(
        0,
        UUID.randomUUID(),
        "Administrador",
        "Prueba",
        "admin@admin.com",
        Password().encriptar(readln()),
        TipoPerfil.ADMINISTRADOR,
        null
    )
    println(meterAdmin.password)
    UsuarioRepositoryImpl(UsuarioDao).save(meterAdmin)

}