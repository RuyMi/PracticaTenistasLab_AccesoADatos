package models

import java.time.LocalDate
import java.util.*

open class Maquina(
    open val numSerie: UUID,
    open val marca: String,
    open val modelo: String,
    open val fechaAdquisicion: LocalDate
    ) {

}
