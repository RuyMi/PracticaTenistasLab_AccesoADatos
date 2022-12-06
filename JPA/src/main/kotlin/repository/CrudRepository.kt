package repository

import java.util.*

/**
 * Crud repository
 *
 * @param T
 * @param ID
 */

interface CrudRepository<T, ID> {
    /**
     * Find all
     *
     * @return
     */
    fun findAll(): List<T> // List<T> es una lista de T

    /**
     * Find by id
     *
     * @param id
     * @return
     */
    fun findById(id: ID): T?

    /**
     * Findby u u i d
     *
     * @param uuid
     * @return
     */
    fun findbyUUID(uuid: UUID): T?

    /**
     * Save
     *
     * @param entity
     * @return
     */
    fun save(entity: T): T

    /**
     * Delete
     *
     * @param entity
     * @return
     */
    fun delete(entity: T): Boolean
}