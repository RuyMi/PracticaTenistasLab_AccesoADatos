package repository.TareasRepository



import models.Tarea
import repositories.CrudRepository




interface TareasRepository : CrudRepository<Tarea, Int> {


}