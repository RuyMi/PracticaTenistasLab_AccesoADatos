package models

import kotlinx.serialization.Serializable
import serializers.UUIDSerializer
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
@Serializable
data class Producto(
    val id:Int,
    @Serializable(UUIDSerializer::class)
    val uuid: UUID= UUID.randomUUID(),
    val marca: String,
    val modelo: String,
    val precio: Double,
    val stock: Int,

    )
