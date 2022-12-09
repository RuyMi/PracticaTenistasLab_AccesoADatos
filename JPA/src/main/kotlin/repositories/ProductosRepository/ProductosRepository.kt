package repository.ProductosRepository



import models.Producto
import repositories.CrudRepository




interface ProductosRepository : CrudRepository<Producto, Int> {


}