package models

import java.util.*

data class Producto(
    val uuid: UUID,
    val marca: String,
    val modelo: String,
    val precio: Double,
    val stock: Int
    )
