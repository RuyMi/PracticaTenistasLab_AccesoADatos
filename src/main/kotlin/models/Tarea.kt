package models

import models.enums.TipoEncordado
import models.enums.TipoNudos
import java.util.*

data class Tarea(

        val uuid: UUID,
        val raqueta: List<Producto>,
        val precio: Double,
        val encordado: TipoEncordado,
        val nudos: TipoNudos
    ){}

