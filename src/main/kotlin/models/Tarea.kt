package models

import java.util.*

open class Tarea(
    val uuid: UUID,
    val raqueta: String,
    val precio: Double,
    val tarea: List<Tarea>
    )
