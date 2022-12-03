package mappers

import entities.MaquinaEncordarDao
import entities.PedidosDao
import models.Maquina
import models.Pedidos

fun PedidosDao.fromPedidosDaoToPedidos():Pedidos {
    return Pedidos(
        id = id.value,
        uuid = uuid,
        estado = estado,
        fechaEntrada = fechaEntrada,
        fechaSalidaProgramada = fechaSalidaProgramada,
        fechaEntrega = fechaEntrega,
        precio = precio,
        tareas = tareas.map { it.fromTareaDaoToTarea() }
    )
}