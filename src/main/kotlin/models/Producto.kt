package models

import java.util.*

data class Producto(
    val id:Int,
    val uuid: UUID= UUID.randomUUID(),
    val marca: String,
    val modelo: String,
    val precio: Double,
    val stock: Int
    )
