package models

import org.hibernate.annotations.Type
import javax.persistence.*
import kotlinx.serialization.Serializable
import serializers.LocalDateSerializer
import serializers.UUIDSerializer
import java.time.LocalDate
import java.util.*


    /**
     * Maquina personalizacion
     *
     * @property id
     * @property numSerie
     * @property marca
     * @property modelo
     * @property fechaAdquisicion
     * @property swingweight
     * @property balance
     * @property rigidez
     * @constructor Create empty Máquina personalizacion
     */
    @Serializable
    @Entity
    @Table(name = "MaquinaPersonalizacion")
    @NamedQueries(
        NamedQuery(name = "MaquinaPer.findAll", query = "SELECT t FROM MaquinaPersonalizacion t"),
        NamedQuery(
            name = "MaquinaPerson.porNumSerie",
            query = "SELECT t FROM MaquinaPersonalizacion t WHERE t.numSerie = :id"
        ),
    )
    data class MaquinaPersonalizacion(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,
        @Serializable(UUIDSerializer::class)
        @Column(name="numSerie_Personalizacion")
        @Type(type = "uuid-char")
        val numSerie: UUID=UUID.randomUUID(),
        val marca: String,
        val modelo: String,
        @Serializable(LocalDateSerializer::class)
        val fechaAdquisicion: LocalDate,
        val swingweight: Boolean,
        val balance: Double,
        val rigidez: Double
    ): java.io.Serializable{
    }







