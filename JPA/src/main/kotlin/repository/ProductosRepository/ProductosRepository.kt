package repository.ProductosRepository



import models.Producto
import repository.CrudRepository



interface ProductosRepository : CrudRepository<Producto,Int>{


}