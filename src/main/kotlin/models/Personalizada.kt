package models

import java.time.LocalDate

import java.util.*

data class Personalizada(
    override val numSerie: UUID,
    override val marca: String,
    override val modelo: String,
    override val fechaAdquisicion: LocalDate,
    val swingweight: Boolean,
    val balance: Double,
    val rigidez: Double
): Maquina(numSerie, marca, modelo, fechaAdquisicion)
