package controller

import db.*

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll

class ControladorTest {

    @Test
    fun listarMaquinasPerso() {
    }

    @Test
    fun encontrarMaquinaPersoID() {
    }

    @Test
    fun encontrarMaquinaPersoUUID() {
    }

    @Test
    fun guardarMaquinaPerso() {
    }

    @Test
    fun borrarMaquinaPerso() {
    }

    @Test
    fun listarMaquinasEncordar() {
    }

    @Test
    fun encontrarMaquinaEncordarID() {
    }

    @Test
    fun encontrarMaquinaEncordarUUID() {
    }

    @Test
    fun guardarMaquinaEncordar() {
    }

    @Test
    fun borrarMaquinaEncordar() {
    }

    @Test
    fun listarPedidos() {
    }

    @Test
    fun encontrarPedidoID() {
    }

    @Test
    fun encontrarPedidoUUID() {
    }

    @Test
    fun guardarPedido() {
    }

    @Test
    fun borrarPedido() {
    }

    @Test
    fun listarProductos() {
    }

    @Test
    fun encontrarProductoID() {
    }

    @Test
    fun encontrarProductoUUID() {
    }

    @Test
    fun guardarProducto() {
    }

    @Test
    fun borrarProducto() {
    }

    @Test
    fun listarTareas() {
    }

    @Test
    fun encontrarTareaID() {
    }

    @Test
    fun encontrarTareaUUID() {
    }

    @Test
    fun guardarTarea() {
    }

    @Test
    fun borrarTarea() {
    }

    @Test
    fun listarUsuarios() {
    }

    @Test
    fun encontrarUsuarioID() {
    }

    @Test
    fun encontrarUsuarioUUID() {
    }

    @Test
    fun guardarUsuario() {
    }

    @Test
    fun borrarUsuario() {
    }

    @Test
    fun listarTurnos() {
    }

    @Test
    fun encontrarTurnoID() {
    }

    @Test
    fun encontrarTurnoUUID() {
    }

    @Test
    fun guardarTurno() {
    }

    @Test
    fun borrarTurno() {
    }

    @Test
    fun getMaquinaEncordarRepositoryImpl() {
    }

    @Test
    fun getMaquinaPersonalizacionRepositoryImpl() {
    }

    @Test
    fun getPedidosRepositoryImpl() {
    }

    @Test
    fun getProductoRepositoryImpl() {
    }

    @Test
    fun getTareaRepositoryImpl() {
    }

    @Test
    fun getUsuarioRepositoryImpl() {
    }

    @Test
    fun getTurnosRepositoryImpl() {
    }

    @Test
    fun getUsuarioActual() {
    }

    companion object{
        @JvmStatic
        @BeforeAll
        fun setUp(): Unit {
            getTurnos().forEach { controlador.guardarTurno(it) }
            val listaTurnos = controlador.listarTurnos()
            listaTurnos.forEach { println(it) }

            getUsuarios().forEach { controlador.guardarUsuario(it!!) }
            val listaUsuarios = controlador.listarUsuarios()
            listaUsuarios.forEach { println(it) }

            getMaquinasEncordar().forEach { controlador.guardarMaquinaEncordar(it) }
            val listaMaquinas = controlador.listarMaquinasEncordar()
            listaMaquinas.forEach { println(it) }

            getMaquinasPersonalizacion().forEach { controlador.guardarMaquinaPerso(it) }
            val listaMaquinasPerso = controlador.listarMaquinasPerso()
            listaMaquinasPerso.forEach { println(it) }

            getProductos().forEach { controlador.guardarProducto(it) }
            val listaProductos = controlador.listarProductos()
            listaProductos.forEach { println(it) }

            getPedidos().forEach { controlador.guardarPedido(it) }
            val listaPedidos = controlador.listarPedidos()
            listaPedidos.forEach { println(it) }

            getTareas().forEach { controlador.guardarTarea(it) }
            val listaTareas = controlador.listarTareas()
            listaTareas.forEach { println(it) }
        }

    }
}