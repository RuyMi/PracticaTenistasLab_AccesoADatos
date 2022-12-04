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
        empleado=empleado.fromUsuarioDaoToUsuario(),
        turno=turno.fromTurnoDaoToTurno(),
        estadoCompletado=estadoCompletado,
        maquinaEncordar = maquinaEncordar?.fromMaquinaEncordadoraDaoToMaquinaEncordar(),
        maquinaPersonalizacion = maquinaPersonalizacion?.fromMaquinaPersonalizacionDaoToMaquinaPersonalizacion(),
        pedido = pedido.fromPedidosDaoToPedidos()
    )
}
