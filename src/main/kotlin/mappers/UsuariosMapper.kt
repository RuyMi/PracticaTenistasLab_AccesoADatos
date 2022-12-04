package mappers

import entities.TurnoDao
import entities.UsuarioDao
import models.Turno
import models.Usuario


fun UsuarioDao.fromUsuarioDaoToUsuario(): Usuario {
    return Usuario(
        id = id.value,
        uuid = uuid,
        nombre=nombre,
        apellido=apellido,
        email=email,
        password=password,
        perfil=perfil,
        turno = turno.fromTurnoDaoToTurno()
    )
}