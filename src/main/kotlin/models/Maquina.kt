package models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import serializers.LocalDateSerializer
import serializers.LocalDateTimeSerializer
import serializers.UUIDSerializer
import java.time.LocalDate
import java.util.*

/**
 * Maquina
 *
 */
@Serializable
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
    @Serializable
    data class MaquinaPersonalizacion(
        val id: Int,
        @Serializable(UUIDSerializer::class)
        val numSerie: UUID=UUID.randomUUID(),
        val marca: String,
        val modelo: String,
        @Serializable(LocalDateSerializer::class)
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
    @Serializable
    data class MaquinaEncordadora(
        val id: Int,
        @Serializable(UUIDSerializer::class)
        val numSerie: UUID = UUID.randomUUID(),
        val marca: String,
        val modelo: String,
        @Serializable(LocalDateSerializer::class)
        val fechaAdquisicion: LocalDate,
        val automatico: Boolean,
        val tensionMaxima: Double,
        val tensionMinima: Double
    )




}
