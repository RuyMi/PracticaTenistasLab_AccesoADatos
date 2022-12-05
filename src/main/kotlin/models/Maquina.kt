package models

import org.jetbrains.exposed.dao.id.EntityID
import java.time.LocalDate
import java.util.*

/**
 * Maquina
 *
 */
sealed class Maquina() {

    /**
     * Maquina personalizacion
     *
     * @property id
     * @property numSerie
     * @property marca
     * @property modelo
     * @property fechaAdquisicion
     * @property swingweight
     * @property balance
     * @property rigidez
     * @constructor Create empty Maquina personalizacion
     */
    data class MaquinaPersonalizacion(
        val id: Int,
        val numSerie: UUID=UUID.randomUUID(),
        val marca: String,
        val modelo: String,
        val fechaAdquisicion: LocalDate,
        val swingweight: Boolean,
        val balance: Double,
        val rigidez: Double
    ){
    }

    /**
     * Maquina encordadora
     *
     * @property id
     * @property numSerie
     * @property marca
     * @property modelo
     * @property fechaAdquisicion
     * @property automatico
     * @property tensionMaxima
     * @property tensionMinima
     * @constructor Create empty Maquina encordadora
     */
    data class MaquinaEncordadora(
        val id: Int,
        val numSerie: UUID = UUID.randomUUID(),
        val marca: String,
        val modelo: String,
        val fechaAdquisicion: LocalDate,
        val automatico: Boolean,
        val tensionMaxima: Double,
        val tensionMinima: Double
    )




}
