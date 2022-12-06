package mappers


import entities.UsuarioDao

import models.Usuario


/**
 * From usuario dao to usuario
 *
 * @return
 */
fun UsuarioDao.fromUsuarioDaoToUsuario(): Usuario {
    return Usuario(
        id = id.value,
        uuid = uuid,
        nombre=nombre,
        apellido=apellido,
        email=email,
        password=password,
        perfil=perfil,
        turno = turno?.fromTurnoDaoToTurno()
    )
}