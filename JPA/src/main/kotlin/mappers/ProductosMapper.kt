package mappers

import entities.ProductoDao
import entities.TareaDao
import models.Producto
import models.Tarea

/**
 * From producto dao to producto
 *
 * @return
 */
fun ProductoDao.fromProductoDaoToProducto(): Producto {
    return Producto(
        id = id.value,
        uuid = uuid,
        marca = marca,
        modelo = modelo,
        precio = precio,
        stock = stock,
    )
}
