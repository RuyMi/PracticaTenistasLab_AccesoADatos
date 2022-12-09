package models

import org.hibernate.annotations.Type
import javax.persistence.*
import kotlinx.serialization.Serializable
import serializers.LocalDateSerializer
import serializers.UUIDSerializer
import java.time.LocalDate
import java.util.*

/**
 * Maquina encordadora
 *
 * @property id
 * @property numSerie
 * @property marca
 * @property modelo
 * @property fechaAdquisicion
 * @property automatico
 * @property tensionMaxima
 * @property tensionMinima
 * @constructor Create empty Maquina encordadora
 */
@Serializable
    @Entity
    @Table(name = "MaquinaEncordadora")
    @NamedQueries(
        NamedQuery(name = "MaquinaEncor.findAll", query = "SELECT t FROM MaquinaEncordadora t"),
        NamedQuery(
            name = "MaquinaEncor.porNumSerie",
            query = "SELECT t FROM MaquinaEncordadora t WHERE t.numSerie = :id"
        ),
    )
    data class MaquinaEncordadora(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int,
        @Serializable(UUIDSerializer::class)
        @Column(name="numSerie_Encordadora")
        @Type(type = "uuid-char")
        val numSerie: UUID = UUID.randomUUID(),
        val marca: String,
        val modelo: String,
        @Serializable(LocalDateSerializer::class)
        val fechaAdquisicion: LocalDate,
        val automatico: Boolean,
        val tensionMaxima: Double,
        val tensionMinima: Double
    ): java.io.Serializable





