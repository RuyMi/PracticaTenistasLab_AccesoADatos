package repository.UsuarioRepository



import models.Usuario
import repositories.CrudRepository

interface UsuarioRepository : CrudRepository<Usuario, Int> {


}