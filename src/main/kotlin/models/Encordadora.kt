package models

import java.time.LocalDate
import java.util.*

data class Encordadora(
    override val numSerie: UUID,
    override val marca: String,
    override val modelo: String,
    override val fechaAdquisicion: LocalDate,
    val automatico: Boolean,
    val tensionMaxima: Double,
    val tensionMinima: Double
): Maquina(numSerie, marca, modelo, fechaAdquisicion)
