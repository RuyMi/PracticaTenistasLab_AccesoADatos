package controller

import models.*
import models.enums.TipoPerfil
import mu.KotlinLogging
import repository.MaquinaEncordarRepository.MaquinaEncordadoraRepositoryImpl
import repository.MaquinaPersonalizacionRepository.MaquinaPersonalizacionRepositoryImpl
import repository.PedidosRepository.PedidosRepositoryImpl
import repository.ProductosRepository.ProductosRepositoryImpl
import repository.TareasRepository.TareasRepositoryImpl
import repository.TurnosRepository.TurnosRepositoryImpl
import repository.UsuarioRepository.UsuarioRepositoryImpl
import java.util.UUID

private val logger = KotlinLogging.logger {}

/**
 * Controlador
 *
 * @property MaquinaEncordarRepositoryImpl
 * @property MaquinaPersonalizacionRepositoryImpl
 * @property PedidosRepositoryImpl
 * @property ProductoRepositoryImpl
 * @property TareaRepositoryImpl
 * @property UsuarioRepositoryImpl
 * @property TurnosRepositoryImpl
 * @property usuarioActual
 * @constructor Create empty Controlador
 */
class Controlador(
    val MaquinaEncordarRepositoryImpl: MaquinaEncordadoraRepositoryImpl,
    val MaquinaPersonalizacionRepositoryImpl: MaquinaPersonalizacionRepositoryImpl,
    val PedidosRepositoryImpl: PedidosRepositoryImpl,
    val ProductoRepositoryImpl: ProductosRepositoryImpl,
    val TareaRepositoryImpl: TareasRepositoryImpl,
    val UsuarioRepositoryImpl: UsuarioRepositoryImpl,
    val TurnosRepositoryImpl: TurnosRepositoryImpl,
    val usuarioActual: Usuario
) {

    //Maquina Personalizacion

    /**
     * Listar maquinas perso
     *
     * @return devuelve una lista de MaquinaPersonalizacion
     */
    fun listarMaquinasPerso(): List<Maquina.MaquinaPersonalizacion> {
        return MaquinaPersonalizacionRepositoryImpl.findAll()
    }

    /**
     * Encontrar maquina perso i d
     *
     * @param id
     * @return  Una MaquinaPersonalizacion
     */
    fun encontrarMaquinaPersoID(id: Int): Maquina.MaquinaPersonalizacion? {
        return if(id > 0){
            MaquinaPersonalizacionRepositoryImpl.findById(id)
        } else{
            logger.debug{"No puedes encontrar una máquina que tenga un id menor que 1. Id introducido: $id"}
            null
        }
    }

    /**
     * Encontrar maquina perso u u i d
     *
     * @param uuid
     * @return Una MaquinaPersonalizacion
     */
    fun encontrarMaquinaPersoUUID(uuid: UUID): Maquina.MaquinaPersonalizacion? {
        return MaquinaPersonalizacionRepositoryImpl.findbyUUID(uuid)
    }

    /**
     * Guardar maquina perso
     *
     * @param maquina
     * @return guarda una MaquinaPersonalizacion
     */
    fun guardarMaquinaPerso(maquina: Maquina.MaquinaPersonalizacion): Maquina.MaquinaPersonalizacion {
        return MaquinaPersonalizacionRepositoryImpl.save(maquina)
    }

    /**
     * Borrar maquina perso
     *
     * @param maquina
     * @return devuelve true si borra la maquina
     */
    fun borrarMaquinaPerso(maquina: Maquina.MaquinaPersonalizacion): Boolean {
        val temp = listarTareas().filter { !it.estadoCompletado }
        return if(temp.count{ it.maquinaPersonalizacion?.numSerie == maquina.numSerie} == 0){
            MaquinaPersonalizacionRepositoryImpl.delete(maquina)
        }else{
            false
        }
    }


    //Maquina Encordar

    /**
     * Listar maquinas encordar
     *
     * @return devuelve una lista de MaquinaEncordadora
     */
    fun listarMaquinasEncordar(): List<Maquina.MaquinaEncordadora> {
        return MaquinaEncordarRepositoryImpl.findAll()
    }

    /**
     * Encontrar maquina encordar id
     *
     * @param id
     * @return devuelve una MaquinaEncordadora
     */
    fun encontrarMaquinaEncordarID(id: Int): Maquina.MaquinaEncordadora? {
        if(id > 0){
            return MaquinaEncordarRepositoryImpl.findById(id)
        } else{
            logger.debug{"No puedes encontrar una máquina que tenga un id menor que 1. Id introducido: $id"}
        }
        return null
    }

    /**
     * Encontrar maquina encordar uuid
     *
     * @param uuid
     * @return devuelve una MaquinaEncordadora
     */
    fun encontrarMaquinaEncordarUUID(uuid: UUID): Maquina.MaquinaEncordadora? {
        return MaquinaEncordarRepositoryImpl.findbyUUID(uuid)
    }

    /**
     * Guardar maquina encordar
     *
     * @param maquina
     * @return guarda una MaquinaEncordadora
     */
    fun guardarMaquinaEncordar(maquina: Maquina.MaquinaEncordadora): Maquina.MaquinaEncordadora {
        return MaquinaEncordarRepositoryImpl.save(maquina)

    }

    /**
     * Borrar maquina encordar
     *
     * @param maquina
     *@return devuelve true si borra la maquina
     */
    fun borrarMaquinaEncordar(maquina: Maquina.MaquinaEncordadora): Boolean {
        val temp = listarTareas().filter { !it.estadoCompletado }
        return if (temp.count { it.maquinaEncordar?.numSerie == maquina.numSerie } == 0) {
            MaquinaEncordarRepositoryImpl.delete(maquina)
        } else {
            false
        }
    }

    /**
     * Listar pedidos
     *
     * @return una lista de Pedidos
     *///Pedidos
    fun listarPedidos(): List<Pedidos> {
        return PedidosRepositoryImpl.findAll()
    }

    /**
     * Encontrar pedido i d
     *
     * @param id
     * @return devuelve un Pedido
     */
    fun encontrarPedidoID(id: Int): Pedidos? {
        if(id > 0){
            return PedidosRepositoryImpl.findById(id)
        } else{
            logger.debug{"No puedes encontrar una máquina que tenga un id menor que 1. Id introducido: $id"}
        }
        return null
    }

    /**
     * Encontrar pedido uuid
     *
     * @param uuid
     * @return devuelve un Pedido
     */
    fun encontrarPedidoUUID(uuid: UUID): Pedidos? {
        return PedidosRepositoryImpl.findbyUUID(uuid)
    }

    /**
     * Guardar pedido
     *
     * @param pedidos
     * @return guarda un Pedidos
     */
    fun guardarPedido(pedidos: Pedidos): Pedidos? {
        if(usuarioActual.perfil == TipoPerfil.ADMINISTRADOR || usuarioActual.perfil == TipoPerfil.USUARIO){
            return PedidosRepositoryImpl.save(pedidos)
        }else{
            logger.debug{"Solo los usuarios o administradores pueden crear pedidos"}
            return null
        }

    }

    /**
     * Borrar Pedido
     *
     * @param pedidos
     * @return devuelve true si borra un pedido
     */
    fun borrarPedido(pedidos: Pedidos): Boolean {
        return PedidosRepositoryImpl.delete(pedidos)
    }

    /**
     * Listar productos
     *
     * @return una lista de Producto
     *///Productos
    fun listarProductos(): List<Producto> {
        return ProductoRepositoryImpl.findAll()
    }

    /**
     * Encontrar producto i d
     *
     * @param id
     * @return devuelve un Producto
     */
    fun encontrarProductoID(id: Int): Producto? {
        if(id > 0){
            return ProductoRepositoryImpl.findById(id)
        } else{
            logger.debug{"No puedes encontrar un producto que tenga un id menor que 1. Id introducido: $id"}
        }
        return null
    }

    /**
     * Encontrar producto u u i d
     *
     * @param uuid
     * @return devuelve un Producto
     */
    fun encontrarProductoUUID(uuid: UUID): Producto? {
        return ProductoRepositoryImpl.findbyUUID(uuid)
    }

    /**
     * Guardar producto
     *
     * @param producto
     * @return guarda un Producto
     */
    fun guardarProducto(producto: Producto): Producto? {
        return if(usuarioActual.perfil == TipoPerfil.ADMINISTRADOR){
            ProductoRepositoryImpl.save(producto)
        }else{
            null
        }
    }

    /**
     * Borrar producto
     *
     * @param producto
     *  @return devuelve un true si borra el producto
     */
    fun borrarProducto(producto: Producto): Boolean {
        return ProductoRepositoryImpl.delete(producto)
    }

    /**
     * Listar tareas
     *
     * @return una lista de Tareas
     *///Tareas
    fun listarTareas(): List<Tarea> {
        return TareaRepositoryImpl.findAll()
    }

    /**
     * Encontrar tarea i d
     *
     * @param id
     * @return devuelve una Tarea
     */
    fun encontrarTareaID(id: Int): Tarea? {
        if(id > 0){
            return TareaRepositoryImpl.findById(id)
        } else{
            logger.debug{"No puedes encontrar una tarea que tenga un id menor que 1. Id introducido: $id"}
        }
        return null
    }

    /**
     * Encontrar tarea u u i d
     *
     * @param uuid
     *@return devuelve una Tarea
     */
    fun encontrarTareaUUID(uuid: UUID): Tarea? {
        return TareaRepositoryImpl.findbyUUID(uuid)
    }

    /**
     * Guardar tarea
     *
     * @param tarea
     * @return guarda una Tarea
     *
     *En caso de que la tarea no este en un turno se podrá añadir ese turno
     * ,si no, no podrá añadirsea otro turno.
     */
    fun guardarTarea(tarea: Tarea): Tarea? {
        val temp = listarTareas()
        val turnoActual = encontrarTurnoUUID(tarea.turno.uuidTurno)
        val empleado = encontrarUsuarioUUID(tarea.empleado.uuid)
        return if (turnoActual != null && empleado != null) {
            val veces = temp.filter { !it.estadoCompletado }.filter { it.turno.uuidTurno == turnoActual.uuidTurno }.count { it.empleado.uuid == empleado.uuid }
            if(veces < 2){
                TareaRepositoryImpl.save(tarea)
            }else{
                logger.debug { "No puede tener 2 tareas en el mismo turno a la vez el empleado con uuid: ${empleado.uuid}"}
                null
            }
        }else{
            logger.debug { "No existe el empleado: ${empleado!!.uuid}"}
            null
        }

    }

    /**
     * Borrar tarea
     *
     * @param tarea
     * @return devuelve un true si se borra
     */
    fun borrarTarea(tarea: Tarea): Boolean {
        return TareaRepositoryImpl.delete(tarea)
    }

    /**
     * Listar usuarios
     *
     * @return devuelve una lista de Usuarios
     *///Usuario
    fun listarUsuarios(): List<Usuario> {
        return UsuarioRepositoryImpl.findAll()
    }

    /**
     * Encontrar usuario i d
     *
     * @param id
     * @return devuelve un Usuario
     */
    fun encontrarUsuarioID(id: Int): Usuario? {
        if(id > 0){
            return UsuarioRepositoryImpl.findById(id)
        } else{
            logger.debug{"No puedes encontrar un usuario que tenga un id menor que 1. Id introducido: $id"}
        }
        return null
    }

    /**
     * Encontrar usuario u u i d
     *
     * @param uuid
     * @return devuelve un Usuario
     */
    fun encontrarUsuarioUUID(uuid: UUID): Usuario? {
        return UsuarioRepositoryImpl.findbyUUID(uuid)
    }

    /**
     * Guardar usuario
     *
     * @param usuario
     *@return devuelve el usuario guardado
     */
    fun guardarUsuario(usuario: Usuario): Usuario {
        return UsuarioRepositoryImpl.save(usuario)
    }

    /**
     * Borrar usuario
     *
     * @param usuario
     * @return devuelve true si borra al usuario
     */
    fun borrarUsuario(usuario: Usuario): Boolean {
        return if(usuario.perfil == TipoPerfil.ENCORDADOR){
            val temp = listarTareas().filter { !it.estadoCompletado }.count { it.empleado.uuid == usuario.uuid }
            if (temp == 0){
                UsuarioRepositoryImpl.delete(usuario)
            }else{
                false
            }
        } else{
            UsuarioRepositoryImpl.delete(usuario)
        }
    }

    /**
     * Listar turnos
     *
     *  @return una lista de turnos
     *///Turnos
    fun listarTurnos(): List<Turno> {
        return TurnosRepositoryImpl.findAll()
    }

    /**
     * Encontrar turno id
     *
     * @param id
     * @return  devuelve un turno
     */
    fun encontrarTurnoID(id: Int): Turno? {
        if(id > 0){
            return TurnosRepositoryImpl.findById(id)
        } else{
            logger.debug{"No puedes encontrar un usuario que tenga un id menor que 1. Id introducido: $id"}
        }
        return null
    }

    /**
     * Encontrar turno uuid
     *
     * @param uuid
     * @return devuelve un turno
     */
    fun encontrarTurnoUUID(uuid: UUID): Turno? {
        return TurnosRepositoryImpl.findbyUUID(uuid)
    }

    /**
     * Guardar turno
     *
     * @param turno
     * @return devuelve el turno guardado
     */
    fun guardarTurno(turno: Turno): Turno {
        return TurnosRepositoryImpl.save(turno)
    }

    /**
     * Borrar turno
     *
     * @param turno
     * @return devuelve true si borra el turno
     */
    fun borrarTurno(turno: Turno): Boolean {
        return TurnosRepositoryImpl.delete(turno)
    }




}