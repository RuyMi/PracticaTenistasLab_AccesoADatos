package mappers

import entities.PedidosDao
import entities.TareaDao
import models.Pedidos
import models.Tarea

fun TareaDao.fromTareaDaoToTarea(): Tarea {
    return Tarea(
        id = id.value,
        uuidTarea = uuidTarea,
        producto =producto.fromProductoDaoToProducto() ,
        precio = precio,
        descripcion = descripcion,
        maquinaEncordar = maquinaEncordar.fromMaquinaEncordadoraDaoToMaquinaEncordar() ,
        maquinaPersonalizacion = maquinaPersonalizacion.fromMaquinaPersonalizacionDaoToMaquinaPersonalizacion()
    )
}
