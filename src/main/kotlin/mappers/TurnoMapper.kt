package mappers

import entities.TurnoDao
import models.Turno

fun TurnoDao.fromTurnoDaoToTurno(): Turno {
    return Turno(
        id = id.value,
        uuidTurno = uuidTurno,
        fechaInicio = fechaInicio,
        fechaFin = fechaFin
    )
}