package mappers

import entities.PedidosDao
import models.Pedidos

/**
 * From pedidos dao to pedidos
 *
 * @return
 */
fun PedidosDao.fromPedidosDaoToPedidos():Pedidos {
    return Pedidos(
        id = id.value,
        uuid = uuid,
        estado = estado,
        fechaEntrada = fechaEntrada,
        fechaSalidaProgramada = fechaSalidaProgramada,
        fechaEntrega = fechaEntrega,
        precio = precio,
        usuario = usuario.fromUsuarioDaoToUsuario()
    )
}