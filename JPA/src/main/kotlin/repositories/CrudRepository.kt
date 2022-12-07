package repositories

import java.util.*

interface CrudRepository<T, ID> {
    fun findAll(): List<T> // List<T> es una lista de T
    fun findById(id: ID): T? // nullable puede no existir
    fun findbyUUID(uuid: UUID): T?
    fun save(entity: T): T // Inserta si no existe, actualiza si existe
    fun delete(entity: T): Boolean // No es obligatorio el boolean
}