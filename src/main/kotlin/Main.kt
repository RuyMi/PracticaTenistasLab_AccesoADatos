import config.AppConfig
import db.DataBaseManager
import entities.MaquinaPersonalizacionDao
import kotlinx.coroutines.runBlocking
import models.Maquina
import org.jetbrains.exposed.sql.idParam
import repository.MaquinaPersonalizacionRepository.MaquinaPersonalizacionRepositoryImpl
import java.time.LocalDate
import java.util.*


fun main(args: Array<String>) = runBlocking {
    initDataBase()
    var maquina = MaquinaPersonalizacionDao
    var repo = MaquinaPersonalizacionRepositoryImpl(maquina)
    var hola=integer
    var prueba = Maquina.MaquinaPersonalizacion(
        idParam()
        numSerie = UUID.randomUUID(),
        marca = "Hola",
        modelo = "hola",
        fechaAdquisicion = LocalDate.now(),
        swingweight = true,
        balance = 20.0,
        rigidez = 20.0


    )

    repo.save(prueba)
    val hola = repo.findAll()
    println(hola)
}

fun initDataBase() {
    val appConfig = AppConfig.fromPropertiesFile("src/main/resources/config.properties")
    println("Configuración: $appConfig")

    // Iniciamos la base de datos con la configuracion que hemos leido
    DataBaseManager.init(appConfig)

}