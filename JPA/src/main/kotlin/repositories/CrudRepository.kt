package repositories

import java.util.*

/**
 * Crud repository
 *
 * @param T
 * @param ID
 * @constructor Create empty Crud repository
 */
interface CrudRepository<T, ID> {
    /**
     * Find all
     *
     * @return una lista de T
     */
    fun findAll(): List<T> // List<T> es una lista de T

    /**
     * Find by id
     *
     * @param id
     * @return devuelve una entidad de tipo T
     */
    fun findById(id: ID): T? // nullable puede no existir

    /**
     * Findby u u i d
     *
     * @param uuid
     * @return devuelve una entidad de Tipo T
     */
    fun findbyUUID(uuid: UUID): T?

    /**
     * Save
     *
     * @param entity
     * @return guarda una entidad de tipo T
     */
    fun save(entity: T): T // Inserta si no existe, actualiza si existe

    /**
     * Delete
     *
     * @param entity
     * @return borra una entidad de tipo T
     */
    fun delete(entity: T): Boolean // No es obligatorio el boolean
}