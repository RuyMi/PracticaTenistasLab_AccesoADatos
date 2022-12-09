package models

import kotlinx.serialization.Serializable
import org.hibernate.annotations.Type

import serializers.UUIDSerializer
import java.util.*
import javax.persistence.*

/**
 * Tarea
 *
 * @property id
 * @property uuidTarea
 * @property producto
 * @property precio
 * @property descripcion
 * @property empleado
 * @property turno
 * @property estadoCompletado
 * @property maquinaEncordar
 * @property maquinaPersonalizacion
 * @property pedido
 * @constructor Create empty Tarea
 */
@Serializable
@Entity
@Table(name = "Tareas")
@NamedQueries(
    NamedQuery(name = "Tareas.findAll", query = "SELECT t FROM Tarea t"),
      NamedQuery(
           name = "Tareas.porUUID",
           query = "SELECT t FROM Tarea t WHERE t.uuidTarea = :id"
       ),

)
data class Tarea(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    @Serializable(UUIDSerializer::class)
    @Column(name="UUID_Tarea")
    @Type(type = "uuid-char")
    val uuidTarea: UUID,
    @ManyToOne
    @JoinColumn(name = "producto_uuid", referencedColumnName = "UUID_Producto")
    val producto: Producto,
    val precio: Double,
    val descripcion: String,
    @ManyToOne
    @JoinColumn(name = "empleado_uuid", referencedColumnName = "UUID_Usuario")
    val empleado:Usuario,
    @ManyToOne
    @JoinColumn(name = "turno_uuid", referencedColumnName = "UUID_Turno")
    val turno:Turno,
    val estadoCompletado:Boolean,
    @ManyToOne
    @JoinColumn(name = "maquinaEncordar_uuid", referencedColumnName = "numSerie_Encordadora", nullable = true)
    val maquinaEncordar: MaquinaEncordadora?,
    @ManyToOne
    @JoinColumn(name = "maquinaPersonalizacion_uuid", referencedColumnName = "numSerie_Personalizacion", nullable = true)
    val maquinaPersonalizacion: MaquinaPersonalizacion?,
    @ManyToOne
    @JoinColumn(name = "pedidos_uuid", referencedColumnName = "UUID_Pedidos")
    val pedido:Pedidos
): java.io.Serializable {
    override fun toString(): String {
        return "Tarea(id=$id, uuidTarea=$uuidTarea, producto=$producto, precio=$precio, descripcion='$descripcion', empleado=${empleado.nombre}, ${empleado.apellido} turno=$turno, estadoCompletado=$estadoCompletado, maquinaEncordar=$maquinaEncordar, maquinaPersonalizacion=$maquinaPersonalizacion, pedido=$pedido)"
    }
}
