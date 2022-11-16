package models

import java.time.LocalDate
import java.util.*

sealed class Maquina() {

    data class MaquinaPersonalizacion(

        val numSerie: UUID,
        val marca: String,
        val modelo: String,
        val fechaAdquisicion: LocalDate,
        val swingweight: Boolean,
        val balance: Double,
        val rigidez: Double
    ){
    }
    data class MaquinaEncordadora(
        val numSerie: UUID,
        val marca: String,
        val modelo: String,
        val fechaAdquisicion: LocalDate,
        val automatico: Boolean,
        val tensionMaxima: Double,
        val tensionMinima: Double
    )
}
