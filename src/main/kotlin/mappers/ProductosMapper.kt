package mappers

import entities.ProductoDao
import entities.TareaDao
import models.Producto
import models.Tarea

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
