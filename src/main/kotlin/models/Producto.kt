package models

import java.util.*

/**
 * Producto
 *
 * @property id
 * @property uuid
 * @property marca
 * @property modelo
 * @property precio
 * @property stock
 * @constructor Create empty Producto
 */
data class Producto(
    val id:Int,
    val uuid: UUID= UUID.randomUUID(),
    val marca: String,
    val modelo: String,
    val precio: Double,
    val stock: Int
    )
