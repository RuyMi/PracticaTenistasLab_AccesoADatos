package models

import kotlinx.serialization.Serializable
import org.hibernate.annotations.Type
import serializers.UUIDSerializer
import java.util.*
import javax.persistence.*


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
@Entity
@Table(name = "Productos")
@NamedQueries(
    NamedQuery(name = "Productos.findAll", query = "SELECT t FROM Producto t"),
    NamedQuery(
        name = "Producto.porUUID",
        query = "SELECT t FROM Producto t WHERE t.uuid = :id"
    ),
)
data class Producto(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int,
    @Serializable(UUIDSerializer::class)
    @Column(name="UUID_Producto")
    @Type(type = "uuid-char")
    val uuid: UUID = UUID.randomUUID(),
    val marca: String,
    val modelo: String,
    val precio: Double,
    val stock: Int
): java.io.Serializable