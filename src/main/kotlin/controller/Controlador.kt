package controller

import models.*
import models.enums.TipoPerfil
import mu.KotlinLogging
import repository.MaquinaEncordarRepository.MaquinaEncordadoraRepositoryImpl
import repository.MaquinaPersonalizacionRepository.MaquinaPersonalizacionRepositoryImpl
import java.util.UUID

private val logger = KotlinLogging.logger {}

class Controlador(
    val MaquinaEncordarRepositoryImpl: MaquinaEncordadoraRepositoryImpl,
    val MaquinaPersonalizacionRepositoryImpl: MaquinaPersonalizacionRepositoryImpl,
    val PedidosRepositoryImpl: PedidosRepositoryImpl,
    val ProductoRepositoryImpl: ProductoRepositoryImpl,
    val TareaRepositoryImpl: TareaRepositoryImpl,
    val UsuarioRepositoryImpl: UsuarioRepositoryImpl,
    val TurnosRepositoryImpl: TurnosRepositoryImpl,
    val usuarioActual: Usuario
) {

    //Maquina Personalizacion

    fun listarMaquinasPerso(): List<Maquina.MaquinaPersonalizacion> {
        return MaquinaPersonalizacionRepositoryImpl.findAll()
    }

    fun encontrarMaquinaPersoID(id: Int): Maquina.MaquinaPersonalizacion? {
        if(id > 0){
            return MaquinaPersonalizacionRepositoryImpl.findById(id)
        } else{
            logger.debug{"No puedes encontrar una máquina que tenga un id menor que 1. Id introducido: $id"}
        }
        return null
    }

    fun encontrarMaquinaPersoUUID(uuid: UUID): Maquina.MaquinaPersonalizacion? {
        return MaquinaPersonalizacionRepositoryImpl.findbyUUID(uuid)
    }

    fun guardarMaquinaPerso(maquina: Maquina.MaquinaPersonalizacion): Maquina.MaquinaPersonalizacion {
        return MaquinaPersonalizacionRepositoryImpl.save(maquina)
    }

    fun borrarMaquinaPerso(maquina: Maquina.MaquinaPersonalizacion): Boolean {
        val temp = listarTareas().filter { !it.estadoCompletado }
        return if(temp.count{ it.maquinaPersonalizacion?.numSerie == maquina.numSerie} == 0){
            MaquinaPersonalizacionRepositoryImpl.delete(maquina)
        }else{
            false
        }
    }


    //Maquina Encordar

    fun listarMaquinasEncordar(): List<Maquina.MaquinaPersonalizacion> {
        return MaquinaPersonalizacionRepositoryImpl.findAll()
    }

    fun encontrarMaquinaEncordarID(id: Int): Maquina.MaquinaEncordadora? {
        if(id > 0){
            return MaquinaEncordarRepositoryImpl.findById(id)
        } else{
            logger.debug{"No puedes encontrar una máquina que tenga un id menor que 1. Id introducido: $id"}
        }
        return null
    }

    fun encontrarMaquinaEncordarUUID(uuid: UUID): Maquina.MaquinaEncordadora? {
        return MaquinaEncordarRepositoryImpl.findbyUUID(uuid)
    }

    fun guardarMaquinaEncordar(maquina: Maquina.MaquinaEncordadora): Maquina.MaquinaEncordadora {
        return MaquinaEncordarRepositoryImpl.save(maquina)

    }

    fun borrarMaquinaEncordar(maquina: Maquina.MaquinaEncordadora): Boolean {
        val temp = listarTareas().filter { !it.estadoCompletado }
        return if (temp.count { it.maquinaEncordar?.numSerie == maquina.numSerie } == 0) {
            MaquinaEncordarRepositoryImpl.delete(maquina)
        } else {
            false
        }
    }

    //Pedidos
    fun listarPedidos(): List<Pedidos> {
        return PedidosRepositoryImpl.findAll()
    }

    fun encontrarPedidoID(id: Int): Maquina.MaquinaEncordadora? {
        if(id > 0){
            return PedidosRepositoryImpl.findById(id)
        } else{
            logger.debug{"No puedes encontrar una máquina que tenga un id menor que 1. Id introducido: $id"}
        }
        return null
    }

    fun encontrarPedidoUUID(uuid: UUID): Pedidos? {
        return PedidosRepositoryImpl.findbyUUID(uuid)
    }

    fun guardarPedido(pedidos: Pedidos): Pedidos {
        return PedidosRepositoryImpl.save(pedidos)
    }

    fun borrarPedido(pedidos: Pedidos): Boolean {
        return PedidosRepositoryImpl.delete(pedidos)
    }

    //Productos
    fun listarProductos(): List<Producto> {
        return ProductoRepositoryImpl.findAll()
    }

    fun encontrarProductoID(id: Int): Producto? {
        if(id > 0){
            return ProductoRepositoryImpl.findById(id)
        } else{
            logger.debug{"No puedes encontrar un producto que tenga un id menor que 1. Id introducido: $id"}
        }
        return null
    }

    fun encontrarProductoUUID(uuid: UUID): Producto? {
        return ProductoRepositoryImpl.findbyUUID(uuid)
    }

    fun guardarProducto(producto: Producto): Producto? {
        return if(usuarioActual.perfil == TipoPerfil.ADMINISTRADOR){
            ProductoRepositoryImpl.save(producto)
        }else{
            null
        }
    }

    fun borrarProducto(producto: Producto): Boolean {
        return ProductoRepositoryImpl.delete(producto)
    }

    //Tareas
    fun listarTareas(): List<Tarea> {
        return TareaRepositoryImpl.findAll()
    }

    fun encontrarTareaID(id: Int): Tarea? {
        if(id > 0){
            return TareaRepositoryImpl.findById(id)
        } else{
            logger.debug{"No puedes encontrar una tarea que tenga un id menor que 1. Id introducido: $id"}
        }
        return null
    }

    fun encontrarTareaUUID(uuid: UUID): Tarea? {
        return TareaRepositoryImpl.findbyUUID(uuid)
    }

    /*
    En caso de que la tarea no este en un turno se podrá añadir ese turno, si no, no podrá añadirse
    a otro turno.
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
                null
            }
        }else{
            null
        }

    }

    fun borrarTarea(tarea: Tarea): Boolean {
        return TareaRepositoryImpl.delete(tarea)
    }

    //Usuario
    fun listarUsuarios(): List<Usuario> {
        return UsuarioRepositoryImpl.findAll()
    }

    fun encontrarUsuarioID(id: Int): Usuario? {
        if(id > 0){
            return UsuarioRepositoryImpl.findById(id)
        } else{
            logger.debug{"No puedes encontrar un usuario que tenga un id menor que 1. Id introducido: $id"}
        }
        return null
    }

    fun encontrarUsuarioUUID(uuid: UUID): Usuario? {
        return UsuarioRepositoryImpl.findbyUUID(uuid)
    }

    fun guardarUsuario(usuario: Usuario): Usuario {
        return UsuarioRepositoryImpl.save(usuario)
    }

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

    //Turnos
    fun listarTurnos(): List<Turno> {
        return TurnosRepositoryImpl.findAll()
    }

    fun encontrarTurnoID(id: Int): Turno? {
        if(id > 0){
            return TurnosRepositoryImpl.findById(id)
        } else{
            logger.debug{"No puedes encontrar un usuario que tenga un id menor que 1. Id introducido: $id"}
        }
        return null
    }

    fun encontrarTurnoUUID(uuid: UUID): Turno? {
        return TurnosRepositoryImpl.findbyUUID(uuid)
    }

    fun guardarTurno(turno: Turno): Turno {
        return TurnosRepositoryImpl.save(turno)
    }

    fun borrarTurno(turno: Turno): Boolean {
        return TurnosRepositoryImpl.delete(turno)
    }




}