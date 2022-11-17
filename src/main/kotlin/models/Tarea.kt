package models

import models.enums.TipoEncordado
import models.enums.TipoNudos
import java.util.*

sealed class Tarea(){
    data class Encordado(
        val uuid: UUID,
        val raqueta: List<Producto>,
        val precio: Double,
        val encordado: TipoEncordado,
        val nudos: TipoNudos
    ){}

    data class Personalizacion(
        val uuid: UUID,
        val raqueta: List<Producto>,
        val precio: Double,
        val peso: Double,
        val balance: Double,
        val rigidez: Double
    )

    data class Adquisicion(
        val uuid: UUID,
        val precio: Double,
    )
}
