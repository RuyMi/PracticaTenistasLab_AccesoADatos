package repository.PedidosRepository



import models.Pedidos
import repositories.CrudRepository




interface PedidosRepository : CrudRepository<Pedidos, Int> {



}