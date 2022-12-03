package models

import org.jetbrains.exposed.dao.id.EntityID
import java.time.LocalDate
import java.util.*

sealed class Maquina() {

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
    data class MaquinaEncordadora(
        val id: Int,
        val numSerie: UUID,
        val marca: String,
        val modelo: String,
        val fechaAdquisicion: LocalDate,
        val automatico: Boolean,
        val tensionMaxima: Double,
        val tensionMinima: Double
    )




}
